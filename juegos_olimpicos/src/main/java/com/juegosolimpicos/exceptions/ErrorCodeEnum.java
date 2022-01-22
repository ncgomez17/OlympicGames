package com.juegosolimpicos.exceptions;

public enum ErrorCodeEnum {

    EXISTS_COUNTRY(409),
    NOT_FOUND_COUNTRY(404),
    FAILED_DEPENDENCY_COUNTRY(424),
    EXISTS_CITY(409),
    NOT_FOUND_CITY(404),
    FAILED_DEPENDECY_CITY(424),
    EXISTS_HEADQUARTER(409),
    NOT_FOUND_HEADQUARTER(404),
    NOT_FOUND_TYPEJJOO(404),
    NOT_FOUND_USER(404),
    EXISTS_USER(409),
    FAILED_DEPENDECY_TYPEJJOO(424),
    UNAUTHORIZED(401),
    NOT_FOUND_AUDIT(404),;


    private int errorCode;

    private ErrorCodeEnum(int errorCode) {
        this.errorCode = errorCode;
    }
    public int getErrorCode(){
        return errorCode;
    }
}
