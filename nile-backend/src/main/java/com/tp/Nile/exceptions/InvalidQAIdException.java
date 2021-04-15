package com.tp.Nile.exceptions;

public class InvalidQAIdException extends Exception {
    public InvalidQAIdException(String message){
        super(message);
    }
    public InvalidQAIdException(String message, Throwable innerException){
        super(message,innerException);
    }
}
