package com.fabiok.sistemahospedaria.application;

import com.fabiok.sistemahospedaria.domain.exceptions.ValidationException;
import com.fabiok.sistemahospedaria.domain.hospede.CadastrarHospede;
import com.fabiok.sistemahospedaria.utils.ObjectMapperProvider;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

public class HospedeHttpHandler implements HttpHandler {
    private static ObjectMapper mapper = ObjectMapperProvider.getMapper();

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        String method = exchange.getRequestMethod();
        CadastrarHospede cadastrarHospede = new CadastrarHospede();
        if(method.equalsIgnoreCase("POST")){
            try (InputStream bodyStream = exchange.getRequestBody()) {
                CadastrarHospedeCommand command = mapper.readValue(bodyStream, CadastrarHospedeCommand.class);
                cadastrarHospede.execute(command);
                exchange.sendResponseHeaders(201, -1);
            } catch (ValidationException e){
                var json = mapper.writeValueAsBytes(Map.of("erros", e.getErros()));
                exchange.sendResponseHeaders(400, json.length);
                exchange.getResponseBody().write(json);
            } catch (Exception e) {
                e.printStackTrace();
                exchange.sendResponseHeaders(400, -1);
            } finally {
                exchange.getResponseBody().close();
            }
        }
    }
}
