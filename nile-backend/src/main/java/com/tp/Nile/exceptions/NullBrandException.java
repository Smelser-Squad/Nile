package com.tp.Nile.exceptions;

public class NullBrandException extends Exception{
    public NullBrandException(String message){
        super(message);
    }
    public NullBrandException(String message, Throwable innerException){
        super(message,innerException);
    }
}
