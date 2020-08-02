package com.example.authenticationsample.infra.http.dtos.user;

import com.example.authenticationsample.domain.user.models.User;

import javax.validation.constraints.NotBlank;

public class CreateUserDTO {

    @NotBlank(message = "O nome é obrigatório")
    private String name;

    @NotBlank(message = "O email é obrigatório")
    private String email;

    @NotBlank(message = "Senha é um campo obrigatório")
    private String password;

    @NotBlank(message = "Confirmação de senha é um campo obrigatório")
    private String password_confirm;

    private String phone;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword_confirm() {
        return password_confirm;
    }

    public void setPassword_confirm(String password_confirm) {
        this.password_confirm = password_confirm;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public User parse() {

        User user = new User();

        user.setPassword(this.getPassword());
        user.setPhone(this.getPhone());
        user.setEmail(this.getEmail());
        user.setName(this.getName());

        return user;
    }
}
