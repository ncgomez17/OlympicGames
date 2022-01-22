package com.juegosolimpicos.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FAILED_DEPENDENCY)
public class FailedDependencyException extends BaseException {

    public FailedDependencyException(ErrorCodeEnum code, String message){
        super(code,message);
    }
}