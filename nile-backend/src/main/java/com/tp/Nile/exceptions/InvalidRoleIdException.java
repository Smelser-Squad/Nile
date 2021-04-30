package com.tp.Nile.exceptions;

public class InvalidRoleIdException extends Exception{
    public InvalidRoleIdException(String message){
        super(message);
    }
    public InvalidRoleIdException(String message, Throwable innerException){
        super(message,innerException);
    }
}
