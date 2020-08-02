package com.example.authenticationsample.infra.authentication;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;

import java.io.Serializable;

public interface IAuthenticationHandler extends Serializable {
    public String generateAuthToken(IAuthenticationData authData);
    public Boolean isValid(String token);
    public String getTokenContent(String token);
}
