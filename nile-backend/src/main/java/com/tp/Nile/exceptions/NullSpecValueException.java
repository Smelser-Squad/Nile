package com.tp.Nile.exceptions;

public class NullSpecValueException extends Exception {
    public NullSpecValueException(String message){
        super(message);
    }
    public NullSpecValueException(String message, Throwable innerException){
        super(message,innerException);
    }
}
