package com.tp.Nile.exceptions;

public class NullRoleIdException extends Exception{
    public NullRoleIdException(String message){
        super(message);
    }
    public NullRoleIdException(String message, Throwable innerException){
        super(message,innerException);
    }
}
