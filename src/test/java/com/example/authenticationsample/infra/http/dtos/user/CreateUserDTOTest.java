package com.example.authenticationsample.infra.http.dtos.user;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CreateUserDTOTest {

    @Test
    void canBeCreated() {
        CreateUserDTO dto = CreateUserDTOMock.generate();
        Assertions.assertNotNull(dto.getPassword());
    }

}
