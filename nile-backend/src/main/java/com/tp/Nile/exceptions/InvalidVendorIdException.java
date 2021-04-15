package com.tp.Nile.exceptions;

public class InvalidVendorIdException extends Exception{
    public InvalidVendorIdException(String message){
        super(message);
    }
    public InvalidVendorIdException(String message, Throwable innerException){
        super(message,innerException);
    }
}
