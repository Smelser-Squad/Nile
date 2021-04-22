package com.tp.Nile.exceptions;

public class NullAnswerException extends Exception{
    public NullAnswerException(String message){
        super(message);
    }
    public NullAnswerException(String message, Throwable innerException){
        super(message,innerException);
    }
}