package com.tp.Nile.exceptions;

public class InvalidSpecValueException extends Exception {
    public InvalidSpecValueException(String message){
        super(message);
    }
    public InvalidSpecValueException(String message, Throwable innerException){
        super(message,innerException);
    }
}
