package com.example.Exam.System.service.impl;

import com.example.Exam.System.dto.*;
import com.example.Exam.System.entity.Exam;
import com.example.Exam.System.entity.Questions;
import com.example.Exam.System.entity.Result;
import com.example.Exam.System.entity.User;
import com.example.Exam.System.repository.ExamRepo;
import com.example.Exam.System.repository.QuestionsRepo;
import com.example.Exam.System.repository.ResultRepo;
import com.example.Exam.System.repository.UserRepo;
import com.example.Exam.System.service.StudentService;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;


@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    ExamRepo examRepo;

    @Autowired
    ResultRepo resultRepo;
    @Autowired
    UserRepo userRepo;

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    QuestionsRepo questionsRepo;

    @Override
    public List<BasicExamDetailsDto> findActiveExams() {
        List<Exam> exams =examRepo.findAllActiveExams();
        return exams.stream()
                .map(exam -> modelMapper.map(exam, BasicExamDetailsDto.class))
                .toList();
    }

    public ViewExamResponseDto viewExam(int id)
    {
        Exam exam= examRepo.findById(id).orElse(null);

        ViewExamResponseDto viewExamResponseDto= modelMapper.map(exam,ViewExamResponseDto.class) ;
        List<QuestionDto> questionDtos = exam.getQuestions()
                .stream()
                .map(question ->modelMapper.map(question,QuestionDto.class) )
                .collect(Collectors.toList());
        viewExamResponseDto.setQuestions(questionDtos);
        return viewExamResponseDto;

    }
    @Override
    public UserResultDto fetchResult(int userId) {
        User user = userRepo.findById(userId).orElse(null);
        UserResultDto userResultDto = modelMapper.map(user, UserResultDto.class);
//        List<ResultDto> results = user.getResult()
//                .stream()
//                .map(result -> modelMapper.map(result, ResultDto.class))
//                .collect(Collectors.toList());
//        for(ResultDto result:results)
//        {
//
//        }
    List<ResultDto> resultDtoList = new ArrayList<>();
        for(Result result : user.getResult()){

            ResultDto resultDto = modelMapper.map(result,ResultDto.class);



            BasicExamResultDetailsDto basicExamResultDetailsDto=modelMapper
                    .map(result.getExams(),BasicExamResultDetailsDto.class);
            resultDto.setExam(basicExamResultDetailsDto);

            resultDtoList.add(resultDto);
        }


        userResultDto.setResult(resultDtoList);
        return userResultDto;
    }

    @Transactional
    public Result submitExam(ExamResponseDto examResponseDto) {

        int correct_answer = 0, wrong_answer = 0;
        for (QuestionResponseDto question :examResponseDto.getQuestions()) {
            Questions ques = questionsRepo.findById(question.getQuestions_id()).orElse(null);
            if (question.getQuestions_id() == ques.getQuestionOption().getId())
                correct_answer++;
            else
                wrong_answer++;
        }
        Result rs = Result.builder()
                .no_of_right_answer(correct_answer)
                .no_of_wrong_answer(wrong_answer)
                .obtained_mark(correct_answer*2)
                .exams(examRepo.findById(examResponseDto.getExam_id()).orElse(null))
                .user(userRepo.findById(examResponseDto.getUser_id()).orElse(null))
                .build();
        return resultRepo.save(rs);


    }
}
