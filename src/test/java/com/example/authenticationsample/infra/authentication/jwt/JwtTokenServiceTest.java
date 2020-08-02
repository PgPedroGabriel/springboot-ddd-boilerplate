package com.example.authenticationsample.infra.authentication.jwt;

import com.example.authenticationsample.domain.user.models.Session;
import com.example.authenticationsample.infra.authentication.IAuthenticationHandler;
import io.jsonwebtoken.lang.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

@SpringBootTest
public class JwtTokenServiceTest {

    @Autowired
    public IAuthenticationHandler jwtTokenService;

    @Test
    void canCreateToken() {

        Session session = new Session(UUID.randomUUID());

        String token = jwtTokenService.generateAuthToken(session);
        Assert.notNull(token);
    }

    @Test
    void canVerifyToken() {
        UUID data = UUID.randomUUID();

        Session session = new Session(data);

        String token = jwtTokenService.generateAuthToken(session);

        Assert.notNull(token);

        String id = jwtTokenService.getTokenContent(token);

        Assert.notNull(id);
        Assert.isTrue(data.toString().equals(id));
    }
}
