package com.example.authenticationsample.infra.authentication;

import com.example.authenticationsample.infra.authentication.jwt.JwtTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationProvider {

    @Autowired
    public IAuthenticationHandler jwtTokenService;

    public String generateAuthToken(IAuthenticationData authData) {
        return this.jwtTokenService.generateAuthToken(authData);
    }

    public String getTokenContent(String token) {
        return this.jwtTokenService.getTokenContent(token);
    }
}
