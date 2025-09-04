package com.example.Exam.System.controller;

import com.example.Exam.System.dto.student.BasicExamDetailsResponseDto;
import com.example.Exam.System.dto.student.CreateExamRequestDto;
import com.example.Exam.System.dto.student.UserResultResponseDto;
import com.example.Exam.System.dto.student.ViewExamResponseDto;
import com.example.Exam.System.service.impl.StudentServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
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
    public ResponseEntity<List<BasicExamDetailsResponseDto>>  viewAllActiveExam()
    {
        return new ResponseEntity<List<BasicExamDetailsResponseDto>>(studentService.findActiveExams(),HttpStatus.OK);
    }

    /// Attempt an exam by selecting options
    @GetMapping("/viewExam/{id}")
    public ViewExamResponseDto viewExam(@PathVariable int id)
    {
        return studentService.viewExam(id);
    }

    /// View personal results
    @GetMapping("/viewResult")
    public ResponseEntity<UserResultResponseDto> ViewResult(HttpServletRequest request)
    {
        String header=request.getHeader("Authorization");
        if(header == null)
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        String token = header.split(" ")[1];
        return new ResponseEntity<UserResultResponseDto>(studentService.fetchResult(token),HttpStatus.OK);
    }

    /// Submit Exam

    @PostMapping("/submit")
    public ResponseEntity<Void> submitExam(@RequestBody CreateExamRequestDto createExamRequestDto)
    {
        studentService.submitExam(createExamRequestDto);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
