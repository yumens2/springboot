package com.example.springbootf.exception;

import org.springframework.http.HttpStatus;

public class UserException extends Exception{
    private String exceptionClass;
    private  HttpStatus httpStatus;

    /**
     * 사용자정의 이샙션구성
     * @param exceptionClass
     * @param httpStatus
     * @param message
     */
    public UserException(String exceptionClass, HttpStatus httpStatus,String message) {
        super(exceptionClass.toString()+","+message);
        this.exceptionClass = exceptionClass;
        this.httpStatus = httpStatus;
    }
}
