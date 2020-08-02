package com.example.authenticationsample.domain.user.models;

import com.example.authenticationsample.infra.libraries.password.PasswordProvider;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

@Entity
@Table(name="users")
public class User implements Serializable {

    @Id
    @org.hibernate.annotations.Type(type="pg-uuid")
    @Column
    private UUID id;

    @Column(unique = true)
    private String email;

    @Column
    private String password;

    @Column
    private String phone;

    @Column
    private String name;


    @Transient
    private String plainPassword;

    public User() {
        if(this.getId() == null) {
            this.setId(UUID.randomUUID());
        }
    }

    public void setPassword(String password) {
        this.plainPassword = password;
        this.password = PasswordProvider.hash(password);
    }

    public String getPlainPassword() {
        return this.plainPassword;
    }

    public boolean isValidPassword(String plainPassword) {
        return PasswordProvider.verify(plainPassword, this.getPassword());
    }


    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
