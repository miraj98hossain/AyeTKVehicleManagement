package com.mhdev.backendservice.utils;

import com.mhdev.commonlib.dto.response.ApiRequestResponse;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ApiRequestResponse> handleEntityNotFoundException(EntityNotFoundException ex) {
        ApiRequestResponse response = new ApiRequestResponse();
        response.setHttpStatus(HttpStatus.BAD_REQUEST.name());
        response.setMessage(ex.getMessage());
        log.error(ex.getMessage(), ex);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ApiRequestResponse> handleIllegalArgumentException(IllegalArgumentException ex) {
        ApiRequestResponse response = new ApiRequestResponse();
        response.setHttpStatus(HttpStatus.BAD_REQUEST.name());
        response.setMessage(ex.getMessage());
        log.error(ex.getMessage(), ex);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiRequestResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        String errorMessage = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .findFirst()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .orElse("Validation error");
        ApiRequestResponse response = new ApiRequestResponse();
        response.setHttpStatus(HttpStatus.BAD_REQUEST.name());
        response.setMessage(errorMessage);
        log.error(ex.getMessage(), ex);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<ApiRequestResponse> handleResponseStatusException(ResponseStatusException ex) {
        ApiRequestResponse response = new ApiRequestResponse();
        response.setHttpStatus(HttpStatus.BAD_REQUEST.name());
        response.setMessage(ex.getReason());
        log.error(ex.getMessage(), ex);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ApiRequestResponse> handleDataIntegrityViolationException(DataIntegrityViolationException ex) {
        ApiRequestResponse response = new ApiRequestResponse();
        response.setHttpStatus(HttpStatus.BAD_REQUEST.name());
        response.setMessage("Database constraint violation: " + extractMeaningfulMessage(ex));
        log.error(ex.getMessage(), ex);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // Fallback for any other unhandled exception
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiRequestResponse> handleGenericException(Exception ex) {
        ApiRequestResponse response = new ApiRequestResponse();
        response.setHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR.name());
        response.setMessage(ex.getMessage());
        log.error(ex.getMessage(), ex);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // Optional: Extract more readable message from nested SQL exception
    private String extractMeaningfulMessage(Throwable ex) {
        while (ex.getCause() != null) {
            ex = ex.getCause();
        }
        return ex.getMessage();
    }
}
