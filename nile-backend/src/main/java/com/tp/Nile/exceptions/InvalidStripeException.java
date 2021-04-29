package com.tp.Nile.exceptions;

public class InvalidStripeException extends Exception {
    public InvalidStripeException(String message){
        super(message);
    }
    public InvalidStripeException(String message, Throwable innerException){
        super(message,innerException);
    }
}
