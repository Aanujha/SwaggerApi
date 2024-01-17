package com.example.SwaggerApi.security;

import lombok.Data;

@Data
public class JwtAuthRespons {

    private String token;
    private String tokenType;
    private Object data;
}
