package com.example.to_do_list_api.api.exceptionhandling;

import com.example.to_do_list_api.domain.exceptions.EmailExistsException;
import com.example.to_do_list_api.domain.exceptions.ResourceNotFoundException;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authorization.AuthorizationDeniedException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);


    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(code = HttpStatus.NOT_FOUND)
    public ErrorResponse handleException(ResourceNotFoundException ex) {
        return new ErrorResponse(HttpStatus.NOT_FOUND, ex.getMessage());
    }

    @ExceptionHandler(EmailExistsException.class)
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public ErrorResponse handleException(EmailExistsException ex) {
        return new ErrorResponse(HttpStatus.BAD_REQUEST, ex.getMessage());
    }

    @ExceptionHandler(InvalidFormatException.class)
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public ErrorResponse handleException(HttpMessageNotReadableException ex) {
        Throwable cause = ex.getCause();
        if (cause instanceof InvalidFormatException ife && ife.getTargetType().isEnum()) {
            List<String> allowedValues = Stream.of(ife.getTargetType().getEnumConstants())
                    .map(Object::toString)
                    .toList();

            String message = String.format("Invalid value %s. Allowed values are: %s", ife.getValue(), allowedValues);
            return new ErrorResponse(HttpStatus.BAD_REQUEST, message);
        }
        return new ErrorResponse(HttpStatus.BAD_REQUEST, "Invalid request");
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            errors.put(error.getField(), error.getDefaultMessage());
        }
        return errors;
    }

    @ExceptionHandler(AuthorizationDeniedException.class)
    @ResponseStatus(code = HttpStatus.FORBIDDEN)
    public ErrorResponse handleException(AuthorizationDeniedException ex) {
        return new ErrorResponse(HttpStatus.FORBIDDEN, "User is not allowed to perform this action");
    }

    @ExceptionHandler(BadCredentialsException.class)
    @ResponseStatus(code = HttpStatus.UNAUTHORIZED)
    public ErrorResponse handleException(BadCredentialsException ex) {
        return new ErrorResponse(HttpStatus.UNAUTHORIZED, "User is not recognized");
    }

    @ExceptionHandler
    @ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponse handleException(Exception ex) {
        logger.error("Unknown error occurred: ", ex);
        return new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Unknown error occurred");
    }


}
