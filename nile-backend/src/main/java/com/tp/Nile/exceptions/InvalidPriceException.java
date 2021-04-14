package com.tp.Nile.exceptions;

public class InvalidPriceException extends Exception {
    public InvalidPriceException(String message){
        super(message);
    }
    public InvalidPriceException(String message, Throwable innerException){
        super(message,innerException);
    }
}
