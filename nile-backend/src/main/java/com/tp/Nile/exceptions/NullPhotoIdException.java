package com.tp.Nile.exceptions;

public class NullPhotoIdException extends Exception {
    public NullPhotoIdException(String message){
        super(message);
    }
    public NullPhotoIdException(String message, Throwable innerException){
        super(message,innerException);
    }
}
