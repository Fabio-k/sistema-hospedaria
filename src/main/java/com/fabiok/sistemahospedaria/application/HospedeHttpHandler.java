package com.fabiok.sistemahospedaria.application;

import com.fabiok.sistemahospedaria.application.mapper.HospedeMapper;
import com.fabiok.sistemahospedaria.domain.Hospede;
import com.fabiok.sistemahospedaria.infra.HospedeDao;
import com.fabiok.sistemahospedaria.infra.Idao;
import com.fabiok.sistemahospedaria.utils.ObjectMapperProvider;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.InputStream;

public class HospedeHttpHandler implements HttpHandler {
    private static ObjectMapper mapper = ObjectMapperProvider.getMapper();

    private Idao<Hospede> hospedeDao = new HospedeDao();

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        String method = exchange.getRequestMethod();
        if(method.equalsIgnoreCase("POST")){
            try (InputStream bodyStream = exchange.getRequestBody()) {
                CadastrarHospedeCommand command = mapper.readValue(bodyStream, CadastrarHospedeCommand.class);
                hospedeDao.save(HospedeMapper.from(command));
                exchange.sendResponseHeaders(201, -1);
            } catch (Exception e) {
                e.printStackTrace();
                exchange.sendResponseHeaders(400, -1);
            } finally {
                exchange.getResponseBody().close();
            }
        }
    }
}
