package com.tp.Nile.exceptions;

public class NullFeatureRatingException extends Exception{
    public NullFeatureRatingException(String message){
        super(message);
    }
    public NullFeatureRatingException(String message, Throwable innerException){
        super(message,innerException);
    }
}
