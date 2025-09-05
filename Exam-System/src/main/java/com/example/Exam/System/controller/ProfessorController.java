package com.example.Exam.System.controller;

import com.example.Exam.System.dto.professor.AddQuestionsRequestDto;
import com.example.Exam.System.dto.professor.CreateExamRequestDto;
import com.example.Exam.System.dto.student.BasicExamDetailsResponseDto;
import com.example.Exam.System.entity.Exam;
import com.example.Exam.System.entity.Result;
import com.example.Exam.System.exception.InvalidRequestException;
import com.example.Exam.System.service.impl.ProfessorServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/professor")
public class ProfessorController {
    private final ProfessorServiceImpl professorService;

    @Autowired
    public ProfessorController(ProfessorServiceImpl professorService) {
        this.professorService = professorService;
    }

    /// View results of all students for a specific exam
    @GetMapping("/viewResults/{id}")
    public ResponseEntity<List<Result>> viewResult(@PathVariable int id)
    {
        return new ResponseEntity<>(professorService.viewAllResults(id), HttpStatus.OK);
    }

    /// Add questions and options
    @PostMapping("/addQuestion/{id}")
    public ResponseEntity<Void> addQuestion(@PathVariable int id, @RequestBody AddQuestionsRequestDto question)
    {
        professorService.addQuestions(id , question);
        return ResponseEntity.ok().build();
    }

    /// Create an exam
    @PostMapping("/createExam")
    public ResponseEntity<Void> createExam(@RequestBody CreateExamRequestDto exam, HttpServletRequest request)
    {
        String header=request.getHeader("Authorization");
        if(header == null)
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        String token = header.split(" ")[1];
        professorService.createExam(exam,token);
        return ResponseEntity.ok().build();
    }



    /// Activate/deactivate an exam
    @PostMapping("/setExamStatus/{id}")
    public ResponseEntity<Void> examStatus(@PathVariable int id)
    {
        professorService.examStatus(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("viwAllExams")
    public ResponseEntity<List<BasicExamDetailsResponseDto>> viewExams(){
        return new ResponseEntity<>(professorService.displayALlExams(), HttpStatus.OK);
    }


}
