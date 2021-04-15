package com.tp.Nile.exceptions;

public class InvalidTypeIdException extends Exception {
    public InvalidTypeIdException(String message){
        super(message);
    }
    public InvalidTypeIdException(String message, Throwable innerException){
        super(message,innerException);
    }
}
