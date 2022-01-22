package com.juegosolimpicos.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class ConflictException extends BaseException {

    public ConflictException(ErrorCodeEnum code, String message){
        super(code,message);
    }
}
