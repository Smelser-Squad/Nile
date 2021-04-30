package com.tp.Nile.exceptions;

public class NullRoleException extends Exception{
    public NullRoleException(String message){
        super(message);
    }
    public NullRoleException(String message, Throwable innerException){
        super(message,innerException);
    }
}
