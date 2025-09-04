package com.example.Exam.System.exception;

public class InvalidRequestException extends RuntimeException{
    public InvalidRequestException(String message){
        super(message);
    }
}
