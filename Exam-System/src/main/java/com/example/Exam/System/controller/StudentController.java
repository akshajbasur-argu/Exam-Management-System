package com.example.Exam.System.controller;

import com.example.Exam.System.dto.*;
import com.example.Exam.System.entity.Exam;
import com.example.Exam.System.entity.Result;
import com.example.Exam.System.service.impl.StudentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/student")

public class StudentController {


    /// View list of active exams
    /// Attempt an exam by selecting options
    /// View personal results
    @Autowired
    StudentServiceImpl studentService;

    /// View list of active exams
    @GetMapping("/viewExam")
    public ResponseEntity<List<BasicExamDetailsDto>>  viewAllActiveExam()
    {
        return new ResponseEntity<List<BasicExamDetailsDto>>(studentService.findActiveExams(),HttpStatus.OK);
    }

    /// Attempt an exam by selecting options
    @GetMapping("/viewExam/{id}")
    public ViewExamResponseDto viewExam(@PathVariable int id)
    {
        return studentService.viewExam(id);
    }

    /// View personal results
    @GetMapping("/viewResult/{id}")
    public ResponseEntity<UserResultDto> ViewResult(@PathVariable int id)
    {
        return new ResponseEntity<UserResultDto>(studentService.fetchResult(id),HttpStatus.OK);
    }

    /// Submit Exam

    @PostMapping("/submit")
    public ResponseEntity<Void> submitExam(@RequestBody ExamResponseDto examResponseDto)
    {
        studentService.submitExam(examResponseDto);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
