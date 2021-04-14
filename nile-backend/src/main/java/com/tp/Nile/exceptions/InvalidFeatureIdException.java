package com.tp.Nile.exceptions;

public class InvalidFeatureIdException extends Exception{
    public InvalidFeatureIdException(String message){
        super(message);
    }
    public InvalidFeatureIdException(String message, Throwable innerException){
        super(message,innerException);
    }
}
