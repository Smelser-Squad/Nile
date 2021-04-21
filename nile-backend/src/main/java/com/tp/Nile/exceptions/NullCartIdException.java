package com.tp.Nile.exceptions;

public class NullCartIdException extends Exception{
    public NullCartIdException(String message){
        super(message);
    }
    public NullCartIdException(String message, Throwable innerException){
        super(message,innerException);
    }
}
