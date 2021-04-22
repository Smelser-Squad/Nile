package com.tp.Nile.exceptions;

public class NullAnswerIdException extends Exception{
    public NullAnswerIdException(String message){
        super(message);
    }
    public NullAnswerIdException(String message, Throwable innerException){
        super(message,innerException);
    }
}