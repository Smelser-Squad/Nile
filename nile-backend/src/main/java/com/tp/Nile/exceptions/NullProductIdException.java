package com.tp.Nile.exceptions;

public class NullProductIdException extends Exception {
    public NullProductIdException(String message){
        super(message);
    }
    public NullProductIdException(String message, Throwable innerException){
        super(message,innerException);
    }
}
