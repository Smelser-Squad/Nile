package com.tp.Nile.exceptions;

public class NullRatingIdException extends Exception{
    public NullRatingIdException(String message){
        super(message);
    }
    public NullRatingIdException(String message, Throwable innerException){
        super(message,innerException);
    }
}
