package com.tp.Nile.exceptions;

public class NullOrderException extends Exception{
    public NullOrderException(String message){
        super(message);
    }
    public NullOrderException(String message, Throwable innerException){
        super(message,innerException);
    }
}
