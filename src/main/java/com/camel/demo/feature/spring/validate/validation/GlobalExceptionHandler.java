package com.camel.demo.feature.spring.validate.validation;

import com.camel.demo.feature.spring.common.BasicResponse;
import com.camel.demo.feature.spring.common.ErrorResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

//For more information, check out this link :
// - https://sdqali.in/blog/2015/12/04/validating-requestparams-and-pathvariables-in-spring-mvc/
// - https://g00glen00b.be/validating-the-input-of-your-rest-api-with-spring/
@ControllerAdvice
@Component
public class GlobalExceptionHandler {

    Logger log = LogManager.getLogger(this.getClass());

    @ExceptionHandler
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    //for POST method
    public ResponseEntity<BasicResponse<Object>> handle(MethodArgumentNotValidException exception) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setCode("INVALID_PARAMETER");
        errorResponse.setMessage("Check errors below.");
        errorResponse.setErrors(exception.getBindingResult().getFieldErrors()
                .stream().map(fieldError -> ErrorResponse.ErrorV1Field.builder()
                        .code("INVALID_FORMAT")
                        .param(fieldError.getField())
                        .message(fieldError.getDefaultMessage())
                        .build())
                .collect(Collectors.toList())
        );

        BasicResponse<Object> basicResponse = new BasicResponse<>();
        basicResponse.setData(errorResponse);
        return new ResponseEntity<>(basicResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    //for GET method
    public ResponseEntity<BasicResponse<Object>> handle(ConstraintViolationException exception) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setCode("INVALID_PARAMETER");
        errorResponse.setMessage("Check errors below.");
        errorResponse.setErrors(exception.getConstraintViolations()
                .stream().map(constraintViolation -> ErrorResponse.ErrorV1Field.builder()
                        .code("INVALID_FORMAT")
                        .param(constraintViolation.getPropertyPath().toString().split("([.])")[1])
                        .message(constraintViolation.getMessage())
                        .build())
                .collect(Collectors.toList())
        );

        BasicResponse<Object> basicResponse = new BasicResponse<>();
        basicResponse.setData(errorResponse);
        return new ResponseEntity<>(basicResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    //for GET method
    public ResponseEntity<BasicResponse<Object>> handle(MissingServletRequestParameterException exception) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setCode("INVALID_PARAMETER");
        errorResponse.setMessage("Check errors below.");
        List<ErrorResponse.ErrorV1Field> errorV1Fields = new ArrayList<>();
        errorV1Fields.add(ErrorResponse.ErrorV1Field.builder()
                .code("MISSING_PARAMETER")
                .param(exception.getParameterName().toString())
                .message("This parameter is require.")
                .build());
        errorResponse.setErrors(errorV1Fields);

        BasicResponse<Object> basicResponse = new BasicResponse<>();
        basicResponse.setData(errorResponse);
        return new ResponseEntity<>(basicResponse, HttpStatus.BAD_REQUEST);
    }
}