package com.fabiok.sistemahospedaria;

import java.io.IOException;
import java.io.InputStream;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

public class QuartoHttpHandler implements HttpHandler
{
	private static final ObjectMapper mapper = new ObjectMapper();

	@Override
	public void handle(HttpExchange exchange) throws IOException {
		String method = exchange.getRequestMethod();
		if(method.equalsIgnoreCase("POST")){
			InputStream bodyStream = exchange.getRequestBody();



			QuartoIdao quartoIdao = new QuartoIdao();
			quartoIdao.save(null);
		}
	}
		
}
