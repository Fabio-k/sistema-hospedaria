package com.fabiok.sistemahospedaria.application;

import com.fabiok.sistemahospedaria.DomainException;
import com.fabiok.sistemahospedaria.domain.exceptions.ValidationException;
import com.fabiok.sistemahospedaria.utils.ObjectMapperProvider;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.util.Map;

public class ErrorHandlerDecorator implements HttpHandler {
    private HttpHandler next;
    private final static ObjectMapper mapper = ObjectMapperProvider.getMapper();

    public ErrorHandlerDecorator(HttpHandler next){
        this.next = next;
    }

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        try {
            next.handle(exchange);
        }catch (ValidationException e){
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
