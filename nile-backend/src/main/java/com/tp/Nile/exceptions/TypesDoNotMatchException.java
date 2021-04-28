package com.tp.Nile.exceptions;

public class TypesDoNotMatchException extends Exception {
    public TypesDoNotMatchException(String message){
        super(message);
    }
    public TypesDoNotMatchException(String message, Throwable innerException){
        super(message,innerException);
    }
}
