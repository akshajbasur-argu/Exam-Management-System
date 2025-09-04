package com.example.Exam.System.service;

import com.example.Exam.System.dto.student.BasicExamDetailsResponseDto;
import com.example.Exam.System.dto.student.CreateExamRequestDto;
import com.example.Exam.System.dto.student.UserResultResponseDto;
import com.example.Exam.System.dto.student.ViewExamResponseDto;
import com.example.Exam.System.entity.Result;

import java.util.List;

public interface StudentService {

    /// View list of active exams
    /// Attempt an exam by selecting options
    /// View personal results

    public List<BasicExamDetailsResponseDto> findActiveExams();
    public UserResultResponseDto fetchResult(String token);
    public ViewExamResponseDto viewExam(int id);
    public Result submitExam(CreateExamRequestDto createExamRequestDto);
}
