package com.tp.Nile.exceptions;

public class InvalidPhotoIdException extends Exception {
    public InvalidPhotoIdException(String message){
        super(message);
    }
    public InvalidPhotoIdException(String message, Throwable innerException){
        super(message,innerException);
    }
}
