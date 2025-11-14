package com.fabiok.sistemahospedaria.application;

import com.fabiok.sistemahospedaria.DomainException;
import com.fabiok.sistemahospedaria.application.command.CadastrarHospedeCommand;
import com.fabiok.sistemahospedaria.application.command.EditarHospedeCommand;
import com.fabiok.sistemahospedaria.domain.Notificacao;
import com.fabiok.sistemahospedaria.domain.exceptions.ValidationException;
import com.fabiok.sistemahospedaria.domain.hospede.AtualizarHospede;
import com.fabiok.sistemahospedaria.domain.hospede.AtualizarStatusHospede;
import com.fabiok.sistemahospedaria.domain.hospede.CadastrarHospede;
import com.fabiok.sistemahospedaria.domain.hospede.ExcluirHospede;
import com.fabiok.sistemahospedaria.domain.hospede.Hospede;
import com.fabiok.sistemahospedaria.domain.hospede.validacoes.ValidarCpf;
import com.fabiok.sistemahospedaria.domain.hospede.validacoes.ValidarEmail;
import com.fabiok.sistemahospedaria.infra.HospedeDao;
import com.fabiok.sistemahospedaria.utils.ObjectMapperProvider;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

public class HospedeHttpHandler implements HttpHandler {
    private static ObjectMapper mapper = ObjectMapperProvider.getMapper();

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        String method = exchange.getRequestMethod();
		HospedeDao hospedeDao = new HospedeDao();
		Notificacao notificacao = new Notificacao();
        CadastrarHospede cadastrarHospede = new CadastrarHospede(hospedeDao, List.of(
			new ValidarCpf(),
			new ValidarEmail()
		), notificacao);
		AtualizarHospede atualizarHospede = new AtualizarHospede(hospedeDao, List.of(
			new ValidarCpf(),
			new ValidarEmail()
		), notificacao);
		ExcluirHospede excluirHospede = new ExcluirHospede(hospedeDao);
		AtualizarStatusHospede atualizarStatusHospede = new AtualizarStatusHospede(hospedeDao);

		try (InputStream bodyStream = exchange.getRequestBody()) {
        	if(method.equalsIgnoreCase("POST")){
                CadastrarHospedeCommand command = mapper.readValue(bodyStream, CadastrarHospedeCommand.class);
                cadastrarHospede.execute(command);
                exchange.sendResponseHeaders(201, -1);
			}
        
			if(method.equalsIgnoreCase("GET")){
				List<Hospede> hospedes = hospedeDao.findAll();
				var json = mapper.writeValueAsBytes(hospedes);
				exchange.sendResponseHeaders(200, json.length);
				exchange.getResponseBody().write(json);
			}

			if(method.equalsIgnoreCase("PATCH")){
				String[] parts = exchange.getRequestURI().getPath().split("/");
				Integer id = Integer.parseInt(parts[2]);
				if(parts.length == 4){
					String status = parts[3];
					atualizarStatusHospede.execute(id, status);
				}
				if(parts.length == 3){
					EditarHospedeCommand cmd = mapper.readValue(bodyStream, EditarHospedeCommand.class);
					atualizarHospede.execute(id, cmd);
					exchange.sendResponseHeaders(204, -1);
				}
			}

			if(method.equalsIgnoreCase("DELETE")){
				String[] parts = exchange.getRequestURI().getPath().split("/");
				if(parts.length == 3) {
					Integer id = Integer.parseInt(parts[2]);
					excluirHospede.execute(id);
					exchange.sendResponseHeaders(204, -1);
				}
			}
		} catch (ValidationException e){
			var json = mapper.writeValueAsBytes(Map.of("erros", e.getErros()));
			exchange.sendResponseHeaders(400, json.length);
			exchange.getResponseBody().write(json);
		} catch(DomainException e){
			var json = mapper.writeValueAsBytes(Map.of("erro", e.getMessage()));
			exchange.sendResponseHeaders(e.getStatus(), json.length);
			exchange.getResponseBody().write(json);
		} catch (Exception e) {
			e.printStackTrace();
			exchange.sendResponseHeaders(500, -1);
		} finally {
			exchange.getResponseBody().close();
		}
	}
    
}
