package com.tp.Nile.exceptions;

public class NullReviewAttributeException extends Exception {

    public NullReviewAttributeException(String message){
        super(message);
    }
    public NullReviewAttributeException(String message, Throwable innerException){
        super(message,innerException);
    }
}
