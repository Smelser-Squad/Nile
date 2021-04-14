package com.tp.Nile.exceptions;

public class NullDescriptionException extends Exception{
    public NullDescriptionException(String message){
        super(message);
    }
    public NullDescriptionException(String message, Throwable innerException){
        super(message,innerException);
    }
}
