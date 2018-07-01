package com.camel.demo.feature.spring.validate.controller;

import com.camel.demo.feature.spring.common.BasicResponse;
import com.camel.demo.feature.spring.validate.domain.TestValidateResponse;
import com.camel.demo.feature.spring.validate.validation.IsValidId;
import com.camel.demo.feature.spring.validate.validation.NotBlankOrNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.Validation;
import javax.validation.Validator;

@RestController
@Validated
public class testController {

    Logger log = LogManager.getLogger(this.getClass());

    @GetMapping("/spring/validate")
    public ResponseEntity<BasicResponse<TestValidateResponse>> testValidation(
            @RequestParam(value = "param_one") @NotBlankOrNull String paramOne,
            @RequestParam(value = "param_two") @NotBlankOrNull String paramTwo
    ) {
        TestValidateResponse response = TestValidateResponse.builder()
                .paramOne(paramOne).build();
        BasicResponse<TestValidateResponse> basicResponse = new BasicResponse<>();
        basicResponse.setData(response);
        return ResponseEntity.ok(basicResponse);
    }

    @PostMapping("/spring/validate")
    public ResponseEntity<BasicResponse<TestValidateResponse>> testValidation(
            @RequestBody @Valid Request request
    ) {
        TestValidateResponse response = TestValidateResponse.builder()
                .paramOne(request.getParamOne()).build();
        BasicResponse<TestValidateResponse> basicResponse = new BasicResponse<>();
        basicResponse.setData(response);
        return ResponseEntity.ok(basicResponse);
    }

    @Data
    @Builder
    @AllArgsConstructor
    public static class Request {
        @NotBlankOrNull
        @IsValidId
        private String paramOne;

        @NotBlankOrNull
        @IsValidId
        private String paramTwo;
    }
}
