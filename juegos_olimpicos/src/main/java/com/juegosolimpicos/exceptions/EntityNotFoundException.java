package com.juegosolimpicos.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class EntityNotFoundException extends BaseException {

    public EntityNotFoundException(ErrorCodeEnum code, String message){
        super(code,message);
    }
}
