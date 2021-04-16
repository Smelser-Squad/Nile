package com.tp.Nile.exceptions;

public class InvalidProductIdException extends Exception{
    public InvalidProductIdException(String message){
        super(message);
    }
    public InvalidProductIdException(String message, Throwable innerException){
        super(message,innerException);
    }
}
