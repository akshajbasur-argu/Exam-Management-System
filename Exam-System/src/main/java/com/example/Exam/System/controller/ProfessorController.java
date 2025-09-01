package com.example.Exam.System.controller;

import com.example.Exam.System.dto.QuestionWithAnswerDto;
import com.example.Exam.System.dto.SaveExamDto;
import com.example.Exam.System.entity.Exam;
import com.example.Exam.System.entity.Result;
import com.example.Exam.System.service.ProfessorService;
import com.example.Exam.System.service.impl.ProfessorServiceImpl;
import com.example.Exam.System.service.impl.StudentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/professor")
public class ProfessorController {

    /// Add questions and options




    @Autowired
    private ProfessorServiceImpl professorService;

    /// View results of all students for a specific exam
    @GetMapping("/viewResults/{id}")
    public ResponseEntity<List<Result>> viewResult(@PathVariable int id)
    {
        return new ResponseEntity<List<Result>>(professorService.viewAllResults(id), HttpStatus.OK);
    }


    @PostMapping("/addQuestion/{id}")
    public ResponseEntity<Void> addQuestion(@PathVariable int id, @RequestBody QuestionWithAnswerDto question)
    {
        professorService.addQuestions(id , question);
        return ResponseEntity.ok().build();
    }

    /// Create an exam
    @PostMapping("/createExam")
    public ResponseEntity<Void> createExam(@RequestBody SaveExamDto exam)
    {
        professorService.createExam(exam);
        return ResponseEntity.ok().build();
    }


    /// Activate/deactivate an exam
    @PostMapping("/setExamStatus")
    public ResponseEntity<Exam> examStatus(@RequestBody Exam exam)
    {
        return new ResponseEntity<Exam>(professorService.examStatus(exam), HttpStatus.OK);
    }

}
