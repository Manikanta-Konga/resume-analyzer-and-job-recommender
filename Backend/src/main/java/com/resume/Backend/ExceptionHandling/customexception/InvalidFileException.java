package com.resume.Backend.exceptionhandling.customexception;

public class InvalidFileException extends RuntimeException{
    public InvalidFileException(String message) {
        super(message);
    }
}
