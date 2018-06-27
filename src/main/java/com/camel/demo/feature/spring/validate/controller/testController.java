package com.camel.demo.feature.spring.validate.controller;

import com.camel.demo.feature.spring.common.BasicResponse;
import com.camel.demo.feature.spring.validate.domain.TestValidateResponse;
import com.camel.demo.feature.spring.validate.validation.IsValidId;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@RestController
@Validated
public class testController {

    @GetMapping("/spring/validate")
    public ResponseEntity<BasicResponse<TestValidateResponse>> testValidation(
            @RequestParam("param_one") @NotBlank @IsValidId String paramOne
    ){
        TestValidateResponse response = TestValidateResponse.builder()
                .paramOne(paramOne).build();
        BasicResponse<TestValidateResponse> basicResponse= new BasicResponse<>();
        basicResponse.setData(response);
        return ResponseEntity.ok(basicResponse);
    }

    @PostMapping("/spring/validate")
    public ResponseEntity<BasicResponse<TestValidateResponse>> testValidation(
            @RequestBody @Valid Request request
    ){
        TestValidateResponse response = TestValidateResponse.builder()
                .paramOne(request.getParamOne()).build();
        BasicResponse<TestValidateResponse> basicResponse= new BasicResponse<>();
        basicResponse.setData(response);
        return ResponseEntity.ok(basicResponse);
    }

    @Data
    public static class Request{
        @IsValidId
        private String paramOne;
    }
}
