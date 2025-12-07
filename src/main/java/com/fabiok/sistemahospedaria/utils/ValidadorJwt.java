package com.fabiok.sistemahospedaria.utils;

import com.nimbusds.jose.JWSVerifier;
import com.nimbusds.jose.crypto.RSASSAVerifier;
import com.nimbusds.jose.jwk.JWK;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;

import java.net.URL;
import java.util.Date;

public class ValidadorJwt {
    private static final String JWT_CERT =
            "http://localhost:8081/realms/sistema-hospedaria/protocol/openid-connect/certs";

    private static JWKSet jwkSet;

    static {
        try {
            jwkSet = JWKSet.load(new URL(JWT_CERT));
        }catch (Exception e){
            throw new RuntimeException();
        }
    }

    public static JWTClaimsSet validar(String token) throws Exception{
        SignedJWT signedJWT = SignedJWT.parse(token);
        JWK jwk = jwkSet.getKeyByKeyId(signedJWT.getHeader().getKeyID());

        if (jwk == null) throw new RuntimeException("key id (kid) inválido");

        RSAKey rsaKey = jwk.toRSAKey();
        JWSVerifier verifier = new RSASSAVerifier(rsaKey);

        if(!signedJWT.verify(verifier)){
            throw new RuntimeException("assinatura inválida");
        }

        JWTClaimsSet claims = signedJWT.getJWTClaimsSet();

        Date now = new Date();
        if (claims.getExpirationTime().before(now)) {
            throw new RuntimeException("Token expirado");
        }

        if (!claims.getIssuer().equals("http://localhost:8081/realms/sistema-hospedaria")) {
            throw new RuntimeException("issuer inválido");
        }

        return claims;

    }
}
