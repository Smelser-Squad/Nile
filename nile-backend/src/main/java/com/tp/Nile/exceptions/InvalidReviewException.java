package com.tp.Nile.exceptions;

public class InvalidReviewException extends Exception{
        public InvalidReviewException(String message){
            super(message);
        }
        public InvalidReviewException(String message, Throwable innerException) {
            super(message, innerException);
        }
}
