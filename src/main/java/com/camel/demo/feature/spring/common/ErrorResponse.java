package com.camel.demo.feature.spring.common;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ErrorResponse {
    private final String id = GeneratorUtility.generateTransactionId();
    private String code;
    private String message;
    private List<ErrorV1Field> errors = new ArrayList<>();

    public void addFieldError(String param, String code, String message) {
        ErrorV1Field error = new ErrorV1Field(code, param, message);
        errors.add(error);
    }

    @Data
    @AllArgsConstructor
    public static class ErrorV1Field implements Serializable {
        private String code;
        private String param;
        private String message;
    }
}
