package com.tp.Nile.exceptions;

public class NullProductSpecIdObjectException extends Exception {
    public NullProductSpecIdObjectException(String message){
        super(message);
    }
    public NullProductSpecIdObjectException(String message, Throwable innerException){
        super(message,innerException);
    }
}
