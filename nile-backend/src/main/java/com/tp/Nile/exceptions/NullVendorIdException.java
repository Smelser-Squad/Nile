package com.tp.Nile.exceptions;

public class NullVendorIdException extends Exception {
    public NullVendorIdException(String message){
        super(message);
    }
    public NullVendorIdException(String message, Throwable innerException){
        super(message,innerException);
    }
}
