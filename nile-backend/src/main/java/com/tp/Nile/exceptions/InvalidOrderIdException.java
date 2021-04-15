package com.tp.Nile.exceptions;

public class InvalidOrderIdException extends Exception{
    public InvalidOrderIdException(String message){
        super(message);
    }
    public InvalidOrderIdException(String message, Throwable innerException){
        super(message,innerException);
    }
}
