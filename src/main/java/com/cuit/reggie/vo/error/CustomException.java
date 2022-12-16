package com.cuit.reggie.vo.error;

public class CustomException extends RuntimeException{
    public CustomException(String message){
        super(message);
    }
}
