package com.tp.Nile.exceptions;

public class NullCategoryIdException extends Exception{
    public NullCategoryIdException(String message){
        super(message);
    }
    public NullCategoryIdException(String message, Throwable innerException){
        super(message,innerException);
    }
}
