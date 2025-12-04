package com.fabiok.sistemahospedaria.application;

import java.io.IOException;
import java.io.InputStream;

import com.fabiok.sistemahospedaria.application.command.CadastrarQuartoCommand;
import com.fabiok.sistemahospedaria.application.mapper.QuartoMapper;
import com.fabiok.sistemahospedaria.infra.QuartoIdao;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

public class QuartoController implements HttpHandler
{
	private static final ObjectMapper mapper = new ObjectMapper();

	@Override
	public void handle(HttpExchange exchange) throws IOException {
		String method = exchange.getRequestMethod();
		if(method.equalsIgnoreCase("POST")){
			InputStream bodyStream = exchange.getRequestBody();
			CadastrarQuartoCommand command = mapper.readValue(bodyStream, CadastrarQuartoCommand.class);
			QuartoIdao quartoIdao = new QuartoIdao();
			quartoIdao.save(QuartoMapper.from(command));
			exchange.sendResponseHeaders(201, -1);
			exchange.getResponseBody().close();
		}
	}
		
}
