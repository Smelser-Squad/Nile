package com.tp.Nile.exceptions;

public class InvalidReviewIdException extends Exception {
    public InvalidReviewIdException(String message){
        super(message);
    }
    public InvalidReviewIdException(String message, Throwable innerException){
        super(message,innerException);
    }
}
