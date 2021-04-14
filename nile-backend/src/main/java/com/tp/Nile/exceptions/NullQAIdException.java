package com.tp.Nile.exceptions;

public class NullQAIdException extends Exception{
    public NullQAIdException(String message){
        super(message);
    }
    public NullQAIdException(String message, Throwable innerException){
        super(message,innerException);
    }
}
