package com.fabiok.sistemahospedaria.service;

import com.fabiok.sistemahospedaria.DomainException;
import com.nimbusds.jwt.JWTClaimsSet;
import com.sun.net.httpserver.HttpExchange;

import java.util.List;
import java.util.Map;

public class AuthorizationService {
    private List<String> roles;

    public AuthorizationService(HttpExchange exchange){
        JWTClaimsSet claims = (JWTClaimsSet) exchange.getAttribute("jwtClaims");

        if(claims == null) throw new RuntimeException("JWT não encontrado");

        roles = extractRoles(claims);
    }

    public void validateAccess(List<String> attributes){
        if(!roles.containsAll(attributes)){
            throw new DomainException("Esse usuário não tem permissão necessária", 401);
        }
    }

    private List<String> extractRoles(JWTClaimsSet claimsSet){
        Map<String, Object> realmAccess = (Map<String, Object>) claimsSet.getClaim("realm_access");
        return (List<String>) realmAccess.get("roles");
    }

    public List<String> getRoles(){
        return List.copyOf(roles);
    }


}
