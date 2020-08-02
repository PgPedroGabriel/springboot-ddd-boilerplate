package com.example.authenticationsample.infra.authentication;

import java.io.Serializable;
import java.util.UUID;

public interface IAuthenticationData extends Serializable {
    public UUID getId();
}
