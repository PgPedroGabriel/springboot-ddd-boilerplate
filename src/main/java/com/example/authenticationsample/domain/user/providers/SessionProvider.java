package com.example.authenticationsample.domain.user.providers;

import com.example.authenticationsample.domain.user.exceptions.InvalidParameters;
import com.example.authenticationsample.domain.user.exceptions.UserInvalidEmail;
import com.example.authenticationsample.domain.user.exceptions.UserNotFound;
import com.example.authenticationsample.domain.user.models.Session;
import com.example.authenticationsample.domain.user.models.User;
import com.example.authenticationsample.infra.authentication.AuthenticationProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class SessionProvider {

    @Autowired
    private AuthenticationProvider authenticationProvider;

    @Autowired
    private UserProvider userProvider;

    public String generateToken(String username, String password) throws InvalidParameters, UserNotFound {

        if(username == null || username.isEmpty()){
            throw new InvalidParameters("Email inválido");
        }

        if(password == null || password.isEmpty()){
            throw new InvalidParameters("senha inválida");
        }

        User user = this.userProvider.findByEmail(username);

        if(!user.isValidPassword(password)) {
            throw new UserNotFound("Usuário não encontrado, verifique seu e-mail e senha");
        }

        Session session = new Session(user.getId());

        String token = this.authenticationProvider.generateAuthToken(session);

        return token;
    }

    public String getTokenContent(String token) {
        return this.authenticationProvider.getTokenContent(token);
    }
}
