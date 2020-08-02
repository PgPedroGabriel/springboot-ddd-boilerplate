package com.example.authenticationsample.domain.user.models;

import com.example.authenticationsample.infra.authentication.IAuthenticationData;

import java.util.UUID;

public class Session implements IAuthenticationData {

    private UUID id;

    @Override
    public UUID getId() {
        return this.id;
    }

    public Session(UUID id) {
        this.id = id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
}
