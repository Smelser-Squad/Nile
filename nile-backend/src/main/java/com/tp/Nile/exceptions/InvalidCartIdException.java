package com.tp.Nile.exceptions;

public class InvalidCartIdException extends Exception{
    public InvalidCartIdException(String message){
        super(message);
    }
    public InvalidCartIdException(String message, Throwable innerException){
        super(message,innerException);
    }
}
