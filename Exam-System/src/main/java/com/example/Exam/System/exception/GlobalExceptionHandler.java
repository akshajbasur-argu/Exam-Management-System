package com.example.Exam.System.exception;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<ApiError> noHandlerFoundException(NoHandlerFoundException ex){
        ApiError apiError= new ApiError("Some Error Occured "+ex.getMessage(),HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(apiError,apiError.getHttpStatus());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiError> methodArgumentNotValidException(MethodArgumentNotValidException ex){
        ApiError apiError= new ApiError("Some Error Occured "+ex.getMessage(),HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(apiError,apiError.getHttpStatus());
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiError> resourceNotFoundException(ResourceNotFoundException ex){
        ApiError apiError= new ApiError("Some Error Occured "+ex.getMessage(),HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(apiError,apiError.getHttpStatus());
    }

    @ExceptionHandler(InvalidRequestException.class)
    public ResponseEntity<ApiError> invalidRequestException(InvalidRequestException ex){
        ApiError apiError= new ApiError("Some Error Occured "+ex.getMessage(),HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(apiError,apiError.getHttpStatus());
    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiError> allExceptions(Exception exception){

        exception.printStackTrace();
        System.out.println(exception.getCause());
        ApiError apiError= new ApiError("Some Error Occured "+exception.getMessage(),HttpStatus.I_AM_A_TEAPOT);
        return new ResponseEntity<>(apiError,apiError.getHttpStatus());
    }
}
