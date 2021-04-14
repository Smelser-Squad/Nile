package com.tp.Nile.exceptions;

public class NullOrderIdException extends Exception{
    public NullOrderIdException(String message){
        super(message);
    }
    public NullOrderIdException(String message, Throwable innerException){
        super(message,innerException);
    }
}
