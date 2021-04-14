package com.tp.Nile.exceptions;

public class NullReviewIdException extends Exception {
    public NullReviewIdException(String message){
        super(message);
    }
    public NullReviewIdException(String message, Throwable innerException){
        super(message,innerException);
    }
}