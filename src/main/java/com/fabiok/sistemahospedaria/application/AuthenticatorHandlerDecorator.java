package com.fabiok.sistemahospedaria.application;

import com.fabiok.sistemahospedaria.utils.ValidadorJwt;
import com.nimbusds.jwt.JWTClaimsSet;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;

public class AuthenticatorHandlerDecorator implements HttpHandler {
    public HttpHandler next;

    public AuthenticatorHandlerDecorator(HttpHandler next){
        this.next = next;
    }

    @Override
    public void handle(HttpExchange exchange) throws IOException {
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
