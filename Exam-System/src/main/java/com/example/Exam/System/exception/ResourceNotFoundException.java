package com.example.Exam.System.exception;

public class ResourceNotFoundException extends RuntimeException{
    public ResourceNotFoundException(String message)
    {
        super(message);
    }
}
