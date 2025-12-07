package com.fabiok.sistemahospedaria.application;

import com.fabiok.sistemahospedaria.utils.ValidadorJwt;
import com.nimbusds.jwt.JWTClaimsSet;
import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;

public class AuthenticatorHandler implements HttpHandler {
    public HttpHandler next;

    public AuthenticatorHandler(HttpHandler next){
        this.next = next;
    }

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        String method = exchange.getRequestMethod();

        Headers headers = exchange.getResponseHeaders();
        headers.add("Access-Control-Allow-Origin", "*");
        headers.add("Access-Control-Allow-Methods", "GET, POST, PUT, PATCH, DELETE, OPTIONS");
        headers.add("Access-Control-Allow-Headers", "Content-Type, Authorization");
        headers.add("Content-Type", "application/json; charset=utf-8");

        if (exchange.getRequestMethod().equalsIgnoreCase("OPTIONS")) {
            exchange.sendResponseHeaders(204, -1);
            return;
        }

        String authHeader = exchange.getRequestHeaders().getFirst("Authorization");

        if(authHeader == null || !authHeader.startsWith("Bearer ")){
            exchange.sendResponseHeaders(401, -1);
            return;
        }

        String token = authHeader.substring(7);

        try {
            JWTClaimsSet jwtClaimsSet = ValidadorJwt.validar(token);
            exchange.setAttribute("jwtClaims", jwtClaimsSet);
            next.handle(exchange);
        }catch (Exception e){
            exchange.sendResponseHeaders(401, -1);
        }
    }
}
