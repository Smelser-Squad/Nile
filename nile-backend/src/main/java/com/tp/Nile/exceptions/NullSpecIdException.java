package com.tp.Nile.exceptions;

public class NullSpecIdException extends Exception {
    public NullSpecIdException(String message){
        super(message);
    }
    public NullSpecIdException(String message, Throwable innerException){
        super(message,innerException);
    }
}
