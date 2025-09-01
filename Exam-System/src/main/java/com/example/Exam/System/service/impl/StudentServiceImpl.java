package com.example.Exam.System.service.impl;

import com.example.Exam.System.dto.QuestionDto;
import com.example.Exam.System.dto.ViewExamResponseDto;
import com.example.Exam.System.entity.Exam;
import com.example.Exam.System.entity.Result;
import com.example.Exam.System.repository.ExamRepo;
import com.example.Exam.System.repository.ResultRepo;
import com.example.Exam.System.service.StudentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    ExamRepo examRepo;

    @Autowired
    ResultRepo resultRepo;

    @Autowired
    ModelMapper modelMapper;

    @Override
    public List<Exam> findActiveExams() {
        return examRepo.findAllActiveExams();
    }
    public ViewExamResponseDto viewExam(int id)
    {
        Exam exam= examRepo.findById(id).orElse(null);

        ViewExamResponseDto viewExamResponseDto= modelMapper.map(exam,ViewExamResponseDto.class) ;
        List<QuestionDto> questionDtos = exam.getQuestions()
                .stream().
                map(question ->modelMapper.map(question,QuestionDto.class) )
                .collect(Collectors.toList());
        viewExamResponseDto.setQuestions(questionDtos);
        return viewExamResponseDto;

    }
    @Override
    public List<Result> fetchResult(int userId) {
        return resultRepo.FetchResultForOne(userId);
    }
}
