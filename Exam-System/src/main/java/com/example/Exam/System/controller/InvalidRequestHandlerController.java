package com.example.Exam.System.controller;

import com.example.Exam.System.exception.InvalidRequestException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class InvalidRequestHandlerController {

    @RequestMapping("/**")
    public void handleInvalidRequest()
    {
        throw new InvalidRequestException("The Request is not valid");
    }
}
