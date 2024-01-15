package com.user.service.exceptions;

import com.user.service.response.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionController {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponse> handlerResourceNotFoundException(ResourceNotFoundException resourceNotFoundException) { // El ResponseEntity retorna un ApiResponse que retorna un message, success y status
        String message = resourceNotFoundException.getMessage();

        ApiResponse response = new ApiResponse(message, true, HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);

    } // Retorna un ApiResponse el cual retorna un message, success y status
}
