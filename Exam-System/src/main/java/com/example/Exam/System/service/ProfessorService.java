package com.example.Exam.System.service;

import com.example.Exam.System.dto.professor.AddQuestionsRequestDto;
import com.example.Exam.System.dto.professor.CreateExamRequestDto;
import com.example.Exam.System.dto.student.BasicExamDetailsResponseDto;
import com.example.Exam.System.entity.Exam;
import com.example.Exam.System.entity.Questions;
import com.example.Exam.System.entity.Result;

import java.util.List;

public interface ProfessorService {

/// Create an exam
/// Add questions and options
/// Activate/deactivate an exam
/// View results of all students for a specific exam

    public Exam createExam(CreateExamRequestDto exam, String token) ;
    public Questions addQuestions(int id , AddQuestionsRequestDto questions);
    public Exam examStatus(int id) ;
    public List<Result> viewAllResults(int id);
    public List<BasicExamDetailsResponseDto> displayALlExams();
}
