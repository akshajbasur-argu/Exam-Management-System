package com.example.Exam.System.service;

import com.example.Exam.System.dto.BasicExamDetailsDto;
import com.example.Exam.System.dto.UserResultDto;
import com.example.Exam.System.entity.Exam;
import com.example.Exam.System.entity.Result;

import java.util.List;

public interface StudentService {

    /// View list of active exams
    /// Attempt an exam by selecting options
    /// View personal results

    public List<BasicExamDetailsDto> findActiveExams();
    public UserResultDto fetchResult(int userId);
}
