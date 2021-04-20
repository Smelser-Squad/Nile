package com.tp.Nile.exceptions;

public class EmptyTypeNameException extends Exception{
    public EmptyTypeNameException(String message){
        super(message);
    }
    public EmptyTypeNameException(String message, Throwable innerException){
        super(message,innerException);
    }
}
