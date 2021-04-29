package com.tp.Nile.exceptions;

public class InvalidRatingIdException extends Exception{
    public InvalidRatingIdException(String message){
        super(message);
    }
    public InvalidRatingIdException(String message, Throwable innerException){
        super(message,innerException);
    }
}
