package com.juegosolimpicos.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class UnauthorizedException extends BaseException{

    public UnauthorizedException(ErrorCodeEnum code, String message){
        super(code,message);
    }
}
