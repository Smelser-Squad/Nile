package com.tp.Nile.exceptions;

public class NullProductObjectException extends Exception{
    public NullProductObjectException(String message){
        super(message);
    }
    public NullProductObjectException(String message, Throwable innerException){
        super(message,innerException);
    }
}
