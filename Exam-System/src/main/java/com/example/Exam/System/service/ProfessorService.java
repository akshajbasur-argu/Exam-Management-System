package com.example.Exam.System.service;

import com.example.Exam.System.dto.QuestionWithAnswerDto;
import com.example.Exam.System.dto.SaveExamDto;
import com.example.Exam.System.entity.Exam;
import com.example.Exam.System.entity.QuestionOption;
import com.example.Exam.System.entity.Questions;
import com.example.Exam.System.entity.Result;

import java.util.List;

public interface ProfessorService {

/// Create an exam
/// Add questions and options
/// Activate/deactivate an exam
/// View results of all students for a specific exam

    public Exam createExam(SaveExamDto exam);
    public Questions addQuestions(int id , QuestionWithAnswerDto questions);
    public QuestionOption addOptions(QuestionOption options );
    public Exam examStatus(Exam exam);
    public List<Result> viewAllResults(int id);

}
