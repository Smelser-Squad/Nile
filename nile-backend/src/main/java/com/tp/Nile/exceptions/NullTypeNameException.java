package com.tp.Nile.exceptions;

public class NullTypeNameException extends Exception{
    public NullTypeNameException(String message){
        super(message);
    }
    public NullTypeNameException(String message, Throwable innerException){
        super(message,innerException);
    }
}
