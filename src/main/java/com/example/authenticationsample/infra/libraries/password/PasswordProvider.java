package com.example.authenticationsample.infra.libraries.password;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public abstract class PasswordProvider {

    private static final PasswordEncoder encoder = new BCryptPasswordEncoder();


    public static String hash(String password) {
        String hashedPassword = encoder.encode(password);

        return hashedPassword;
    }

    public static boolean verify(String plainPassword, String hashedPassword) {
        return encoder.matches(plainPassword, hashedPassword);
    }

}
