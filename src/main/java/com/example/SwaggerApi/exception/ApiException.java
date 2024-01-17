package com.example.SwaggerApi.exception;

public class ApiException extends Throwable {
    public ApiException(String message) {
        super(message);

    }

    public ApiException() {
        super();

    }
}
