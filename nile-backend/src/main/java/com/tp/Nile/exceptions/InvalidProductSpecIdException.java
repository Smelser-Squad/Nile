package com.tp.Nile.exceptions;

public class InvalidProductSpecIdException extends Exception {
    public InvalidProductSpecIdException(String message){
        super(message);
    }
    public InvalidProductSpecIdException(String message, Throwable innerException){
        super(message,innerException);
    }
}
