package com.tp.Nile.exceptions;

public class MissingPropertyException extends Exception{
    public MissingPropertyException(String message){
        super(message);
    }
    public MissingPropertyException(String message, Throwable innerException){
        super(message,innerException);
    }
}
