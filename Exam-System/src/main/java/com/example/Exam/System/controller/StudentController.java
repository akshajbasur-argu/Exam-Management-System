package com.example.Exam.System.controller;

import com.example.Exam.System.dto.ViewExamResponseDto;
import com.example.Exam.System.service.impl.StudentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/student")

public class StudentController {

    @Autowired
    StudentServiceImpl studentService;

    @GetMapping("viewExam/{id}")
    public ViewExamResponseDto viewExam(@PathVariable int id)
    {
        return studentService.viewExam(id);
    }
}
