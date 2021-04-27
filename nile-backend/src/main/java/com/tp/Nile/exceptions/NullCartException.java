package com.tp.Nile.exceptions;

public class NullCartException extends Exception{
    public NullCartException(String message){
        super(message);
    }
    public NullCartException(String message, Throwable innerException){
        super(message,innerException);
    }
}