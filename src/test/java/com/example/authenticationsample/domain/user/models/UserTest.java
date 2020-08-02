package com.example.authenticationsample.domain.user.models;

import io.jsonwebtoken.lang.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserTest {

    @Test
    void canBeCreated() {

        User user = UserMock.generate();

        Assert.isTrue(!user.getName().isEmpty());
        Assert.isTrue(!user.getPassword().isEmpty());
        Assert.isTrue(!user.getEmail().isEmpty());
        Assert.isTrue(!user.getPhone().isEmpty());
        Assert.isTrue(!user.getId().toString().isEmpty());

    }

}
