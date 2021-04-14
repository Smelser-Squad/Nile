package com.tp.Nile.exceptions;

public class InvalidCategoryIdException extends Exception{
    public InvalidCategoryIdException(String message){
        super(message);
    }
    public InvalidCategoryIdException(String message, Throwable innerException){
        super(message,innerException);
    }
}
