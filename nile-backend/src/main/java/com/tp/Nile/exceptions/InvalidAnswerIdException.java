package com.tp.Nile.exceptions;

public class InvalidAnswerIdException extends Exception{
    public InvalidAnswerIdException(String message){
        super(message);
    }
    public InvalidAnswerIdException(String message, Throwable innerException){
        super(message,innerException);
    }
}