package com.tp.Nile.exceptions;

public class InvalidSpecIdException extends Exception{
    public InvalidSpecIdException(String message){
        super(message);
    }
    public InvalidSpecIdException(String message, Throwable innerException){
        super(message,innerException);
    }
}

