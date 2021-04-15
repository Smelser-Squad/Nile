package com.tp.Nile.exceptions;

public class NullPriceException extends Exception{
    public NullPriceException(String message){
        super(message);
    }
    public NullPriceException(String message, Throwable innerException){
        super(message,innerException);
    }
}
