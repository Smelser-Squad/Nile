package com.tp.Nile.exceptions;

public class InvaildQAIdException extends Exception {
    public InvaildQAIdException(String message){
        super(message);
    }
    public InvaildQAIdException(String message, Throwable innerException){
        super(message,innerException);
    }
}
