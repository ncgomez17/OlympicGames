package com.juegosolimpicos.ws.exceptions;

import com.juegosolimpicos.exceptions.*;
import com.juegosolimpicos.openapi.model.ErrorDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.OffsetDateTime;

@RestControllerAdvice
public class ControllerExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(ControllerExceptionHandler.class);

    @ExceptionHandler({EntityNotFoundException.class, ConflictException.class, FailedDependencyException.class,
                        UnauthorizedException.class})
    public ResponseEntity<ErrorDto> handleExceptions(final BaseException ex) {
        final long timestamp = System.currentTimeMillis();
        log.error("HandleException: {} {} at {}",ex.getCode(),ex.getMessage(),timestamp);
        final HttpStatus httpStatus = ex.getClass().isAnnotationPresent(ResponseStatus.class)
                ? ex.getClass().getAnnotation(ResponseStatus.class).value() : HttpStatus.INTERNAL_SERVER_ERROR;

        final String errorMessage = ex.getMessage();

        final ErrorDto response = new ErrorDto();
        response.setMessage(errorMessage);
        response.setCode(ex.getCode().getErrorCode());
        response.setDate( OffsetDateTime.now());
        return new ResponseEntity<>(response,httpStatus);
    }

}
