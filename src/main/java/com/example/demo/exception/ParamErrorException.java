package com.example.demo.exception;


public class ParamErrorException extends Exception{

    String message;

    public ParamErrorException(String message, String message1) {
        super(message);
        this.message = message1;
    }
    public ParamErrorException() {

    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
