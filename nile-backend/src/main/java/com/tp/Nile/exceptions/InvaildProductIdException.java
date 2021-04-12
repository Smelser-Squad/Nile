package com.tp.Nile.exceptions;

public class InvaildProductIdException extends Exception{
    public InvaildProductIdException(String message){
        super(message);
    }
    public InvaildProductIdException(String message, Throwable innerException){
        super(message,innerException);
    }
}
