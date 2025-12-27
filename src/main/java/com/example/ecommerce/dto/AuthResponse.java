package com.example.ecommerce.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthResponse {
    private String jwt;
    private String token;

    public AuthResponse(String jwt) {
        this.jwt = jwt;
        this.token = jwt;
    }
}
