package com.fabiok.sistemahospedaria.application;

import com.fabiok.sistemahospedaria.application.command.CadastrarHospedeCommand;
import com.fabiok.sistemahospedaria.domain.exceptions.ValidationException;
import com.fabiok.sistemahospedaria.domain.hospede.CadastrarHospede;
import com.fabiok.sistemahospedaria.domain.hospede.Hospede;
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
        CadastrarHospede cadastrarHospede = new CadastrarHospede();
		HospedeDao hospedeDao = new HospedeDao();
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
		} catch (ValidationException e){
			var json = mapper.writeValueAsBytes(Map.of("erros", e.getErros()));
			exchange.sendResponseHeaders(400, json.length);
			exchange.getResponseBody().write(json);
		} catch (Exception e) {
			e.printStackTrace();
			exchange.sendResponseHeaders(500, -1);
		} finally {
			exchange.getResponseBody().close();
		}
	}
    
}
