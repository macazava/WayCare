package com.waycare.waycare2.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class RegistarRequest {
    @NotBlank(message = "Nome é obrigatório")
    public String nome;

    @Email(message = "Email inválido")
    @NotBlank(message = "Email é obrigatório")
    private String email;

    @NotBlank(message = "Password é obrigatória")
    @Size(min = 8, message = "A password deve ter pelo menos 8 caracteres")
    private String password;

    // Getters e Setters obrigatórios para o Spring conseguir ler os campos
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
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

}
