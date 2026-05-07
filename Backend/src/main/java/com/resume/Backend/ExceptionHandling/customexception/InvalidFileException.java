package com.resume.Backend.ExceptionHandling.customexception;

public class InvalidFileException extends RuntimeException{
    public InvalidFileException(String message) {
        super(message);
    }
}
