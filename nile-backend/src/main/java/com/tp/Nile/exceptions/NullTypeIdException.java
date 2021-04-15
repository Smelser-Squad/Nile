package com.tp.Nile.exceptions;

public class NullTypeIdException extends Exception{
    public NullTypeIdException(String message){
        super(message);
    }
    public NullTypeIdException(String message, Throwable innerException){
        super(message,innerException);
    }
}
