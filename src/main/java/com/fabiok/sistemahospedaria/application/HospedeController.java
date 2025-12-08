package com.fabiok.sistemahospedaria.application;

import com.fabiok.sistemahospedaria.DomainException;
import com.fabiok.sistemahospedaria.application.command.AtualizarHospedeCommand;
import com.fabiok.sistemahospedaria.application.command.CadastrarHospedeCommand;
import com.fabiok.sistemahospedaria.application.dto.FiltroHospedeDto;
import com.fabiok.sistemahospedaria.application.dto.PageResponse;
import com.fabiok.sistemahospedaria.domain.Notificacao;
import com.fabiok.sistemahospedaria.domain.exceptions.ValidationException;
import com.fabiok.sistemahospedaria.domain.hospede.*;
import com.fabiok.sistemahospedaria.domain.hospede.validacoes.ValidarCpf;
import com.fabiok.sistemahospedaria.domain.hospede.validacoes.ValidarEmail;
import com.fabiok.sistemahospedaria.infra.HospedeDao;
import com.fabiok.sistemahospedaria.infra.HospedePostgresDao;
import com.fabiok.sistemahospedaria.infra.HospedeSqliteDao;
import com.fabiok.sistemahospedaria.service.AutorizacaoService;
import com.fabiok.sistemahospedaria.utils.ObjectMapperProvider;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.InputStream;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HospedeController implements HttpHandler {
    private static ObjectMapper mapper = ObjectMapperProvider.getMapper();

    @Override
    public void handle(HttpExchange exchange) throws IOException {
		AutorizacaoService authorizationService = new AutorizacaoService(exchange);

        String method = exchange.getRequestMethod();
		HospedeDao hospedeDao = authorizationService.getRoles().contains("db:sqlite") ? new HospedeSqliteDao() : new HospedePostgresDao();
		Notificacao notificacao = new Notificacao();
        CadastrarHospede cadastrarHospede = new CadastrarHospede(hospedeDao, List.of(
			new ValidarCpf(hospedeDao),
			new ValidarEmail()
		), notificacao);
		AtualizarHospede atualizarHospede = new AtualizarHospede(hospedeDao, List.of(
			new ValidarCpf(hospedeDao),
			new ValidarEmail()
		), notificacao);
		ExcluirHospede excluirHospede = new ExcluirHospede(hospedeDao);
		AtualizarStatusHospede atualizarStatusHospede = new AtualizarStatusHospede(hospedeDao);
		BuscarHospede buscarHospede = new BuscarHospede(hospedeDao);



		try (InputStream bodyStream = exchange.getRequestBody()) {
        	if(method.equalsIgnoreCase("POST")){
                CadastrarHospedeCommand command = mapper.readValue(bodyStream, CadastrarHospedeCommand.class);
                cadastrarHospede.executar(command);
                exchange.sendResponseHeaders(201, -1);
			}
        
			if(method.equalsIgnoreCase("GET")){
				String[] parts = exchange.getRequestURI().getPath().split("/");

				if(parts.length == 3){
					Integer id = Integer.parseInt(parts[2]);
					Hospede hospede = buscarHospede.executar(id);
					gerarRespostaJson(hospede, exchange, 200);
				}else{
					FiltroHospedeDto filtroHospedeDto = mapper.convertValue(getQueryParam(exchange), FiltroHospedeDto.class);
				    PageResponse<Hospede> hospedes = hospedeDao.findAll(filtroHospedeDto);
					gerarRespostaJson(hospedes, exchange, 200);
				}
			}

			if(method.equalsIgnoreCase("PATCH")){
				String[] parts = exchange.getRequestURI().getPath().split("/");
				Integer id = Integer.parseInt(parts[2]);
				if(parts.length == 4){
					authorizationService.validarAcesso(List.of("hospede:status"));
					String status = parts[3];
					atualizarStatusHospede.executar(id, status);
					exchange.sendResponseHeaders(204, -1);
				}
				if(parts.length == 3){
					authorizationService.validarAcesso(List.of("hospede:edit"));
				    AtualizarHospedeCommand cmd = mapper.readValue(bodyStream, AtualizarHospedeCommand.class);
					atualizarHospede.executar(id, cmd);
					exchange.sendResponseHeaders(204, -1);
				}
			}

			if(method.equalsIgnoreCase("DELETE")){
				authorizationService.validarAcesso(List.of("hospede:delete"));
				String[] parts = exchange.getRequestURI().getPath().split("/");
				if(parts.length == 3) {
					Integer id = Integer.parseInt(parts[2]);
					excluirHospede.executar(id);
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

	public void gerarRespostaJson(Object entity, HttpExchange exchange, Integer status) throws JsonProcessingException, IOException {
		var json = mapper.writeValueAsBytes(entity);
		exchange.sendResponseHeaders(status, json.length);
		exchange.getResponseBody().write(json);
	}

	public Map<String, String> getQueryParam(HttpExchange httpExchange){
		String query = httpExchange.getRequestURI().getQuery();
		Map<String, String> params = new HashMap<>();

		if(query == null || query.isBlank()){
			return params;
		}

		for(String param : query.split("&")){
			String[] pair = param.split("=");
			if(pair.length != 2) continue;
			String key = URLDecoder.decode(pair[0], StandardCharsets.UTF_8);
			String value = URLDecoder.decode(pair[1], StandardCharsets.UTF_8);
			params.put(key,value);
		}

		return params;
	}
}
