package com.example.authenticationsample.infra.libraries.password;

import io.jsonwebtoken.lang.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class PasswordProviderTest {

    @Test
    void canHashedAndCheckPassword() {

        String plainPassword = "abcdefghij";

        String hashed = PasswordProvider.hash(plainPassword);

        Assert.isTrue(!plainPassword.equals(hashed));

        Assert.isTrue(PasswordProvider.verify(plainPassword, hashed));

    }
}
