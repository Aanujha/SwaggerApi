package com.example.SwaggerApi.security;

import lombok.Data;

@Data
public class JwtAuthRequest {

   // private String username;

    private String password;

    private String getCustomername;

}
