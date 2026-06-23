package com.sistema.prestamos.infrastructure.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

public class AuthResponseDTO {
    private String token;

    public AuthResponseDTO() {
    }

    public AuthResponseDTO(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
