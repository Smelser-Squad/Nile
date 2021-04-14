package com.tp.Nile.exceptions;

public class NullUserIdException extends Exception {
    public NullUserIdException(String message){
        super(message);
    }
    public NullUserIdException(String message, Throwable innerException){
        super(message,innerException);
    }
}
