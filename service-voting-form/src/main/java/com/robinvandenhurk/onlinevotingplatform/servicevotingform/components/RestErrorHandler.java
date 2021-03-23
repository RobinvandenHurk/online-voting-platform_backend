package com.robinvandenhurk.onlinevotingplatform.servicevotingform.components;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.robinvandenhurk.onlinevotingplatform.servicevotingform.domain.http.response.HttpResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.IOException;
import java.io.PushbackInputStream;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

/**
 * Author:    Robin van den Hurk
 * Date:      22/03/2021
 * File name: RestErrorHandler
 */

@ControllerAdvice
public class RestErrorHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public HttpResponse<?> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });

        return HttpResponse.createBadRequest("Invalid parameters received", errors);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(InvalidFormatException.class)
    public HttpResponse<?> handleValidationExceptions(InvalidFormatException ex) {
        String path = ex.getPathReference();
        String message = path.split("\\[")[1];
        message = message.split("\\]")[0];
        String variableName = message.replace("\"", "");

        Map<String, String> errors = new HashMap<>();
        errors.put(variableName, "Invalid format");

        return HttpResponse.createBadRequest("Invalid parameters received", errors);
    }
}
