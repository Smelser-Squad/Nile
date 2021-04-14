package com.tp.Nile.exceptions;

public class NullFeatureIdException extends Exception{
    public NullFeatureIdException(String message){
        super(message);
    }
    public NullFeatureIdException(String message, Throwable innerException){
        super(message,innerException);
    }
}
