package com.tp.Nile.exceptions;

public class NullNameException extends Exception {
    public NullNameException(String message){
        super(message);
    }
    public NullNameException(String message, Throwable innerException){
        super(message,innerException);
    }
}
