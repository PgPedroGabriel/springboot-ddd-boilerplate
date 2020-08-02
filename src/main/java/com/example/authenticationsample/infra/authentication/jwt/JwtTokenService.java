package com.example.authenticationsample.infra.authentication.jwt;

import com.example.authenticationsample.infra.authentication.IAuthenticationData;
import com.example.authenticationsample.infra.authentication.IAuthenticationHandler;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.security.Key;

@Service
@Qualifier("jwtTokenService")
public class JwtTokenService implements IAuthenticationHandler {

    private Key key;

    JwtTokenService() {
        this.key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
    }


    @Override
    public String generateAuthToken(IAuthenticationData tokenData) {

        String token = Jwts.builder().setSubject(tokenData.getId().toString()).signWith(this.key).compact();

        return token;
    }

    public String getTokenContent(String token) throws JwtException {

        Jws<Claims> jws =  Jwts.parserBuilder().setSigningKey(this.key).build().parseClaimsJws(token);

        return jws.getBody().getSubject();
    }

    @Override
    public Boolean isValid(String token) {

        try {
            Jws<Claims> jws = Jwts.parserBuilder().setSigningKey(this.key).build().parseClaimsJws(token);

            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
