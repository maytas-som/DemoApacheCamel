package com.camel.demo.feature.spring.common;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
public class ErrorResponse {
    private final String id = GeneratorUtility.generateTransactionId();
    private String code;
    private String message;
    private List<ErrorV1Field> errors = new ArrayList<>();

    public void setErrors(List<ErrorV1Field> errors) {
        this.errors = errors;
    }

    public void addFieldError(String param, String code, String message) {
        ErrorV1Field error = new ErrorV1Field(code, param, message);
        errors.add(error);
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ErrorV1Field implements Serializable {
        private String code;
        private String param;
        private String message;
    }
}
