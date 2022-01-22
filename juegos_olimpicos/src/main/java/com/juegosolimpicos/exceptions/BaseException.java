package com.juegosolimpicos.exceptions;

import lombok.Getter;

@Getter
public class BaseException extends Exception{

    private String message;
    private ErrorCodeEnum code;

    public BaseException(ErrorCodeEnum code,String message){
        super();
        this.message = message;
        this.code = code;
    }
}
