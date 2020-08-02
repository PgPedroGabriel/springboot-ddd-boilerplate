package com.example.authenticationsample.infra.http.dtos.user;

import com.github.javafaker.Faker;

import java.util.Locale;

public abstract class CreateUserDTOMock {


    public static CreateUserDTO generate() {
        CreateUserDTO dto = new CreateUserDTO();

        Faker faker = new Faker(new Locale("pt-BR"));

        dto.setPassword(faker.internet().password());
        dto.setPassword_confirm(dto.getPassword());

        dto.setName(faker.name().fullName());
        dto.setEmail(faker.internet().emailAddress());
        dto.setPhone(faker.phoneNumber().cellPhone());

        return dto;
    }


}
