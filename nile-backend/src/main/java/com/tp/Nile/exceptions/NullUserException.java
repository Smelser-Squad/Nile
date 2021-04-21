package com.tp.Nile.exceptions;

public class NullUserException extends Exception{
    public NullUserException(String message){
        super(message);
    }
    public NullUserException(String message, Throwable innerException){
        super(message,innerException);
    }
}