package com.example.authenticationsample.domain.user.models;

import com.github.javafaker.Faker;

import java.util.Locale;

public abstract class UserMock {

    public static User generate() {
        User u = new User();

        Faker faker = new Faker(new Locale("pt-BR"));

        String name = faker.name().fullName();
        String password = faker.internet().password();
        String phone = faker.phoneNumber().cellPhone();
        String email = faker.internet().emailAddress();

        u.setName(name);
        u.setPassword(password);
        u.setPhone(phone);
        u.setEmail(email);


        return u;
    }
}
