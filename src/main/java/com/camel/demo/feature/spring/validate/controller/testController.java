package com.camel.demo.feature.spring.validate.controller;

import com.camel.demo.feature.spring.common.BasicResponse;
import com.camel.demo.feature.spring.validate.domain.TestValidateResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class testController {

    @GetMapping("/spring/validate")
    public ResponseEntity<BasicResponse<TestValidateResponse>> testValidation(
            @RequestParam("param_one") String paramOne
    ){
        TestValidateResponse response = TestValidateResponse.builder()
                .paramOne(paramOne).build();
        BasicResponse<TestValidateResponse> basicResponse= new BasicResponse<>();
        basicResponse.setData(response);
        return ResponseEntity.ok(basicResponse);
    }
}
