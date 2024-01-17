package com.example.SwaggerApi.dto;

import lombok.Data;

@Data
public class CustomerDto {

    private Long id;

    private String customername;
    private String status;
    private String email;
    private String password;

}
