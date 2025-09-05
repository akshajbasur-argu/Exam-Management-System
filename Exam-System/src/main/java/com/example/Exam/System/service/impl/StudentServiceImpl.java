package com.example.Exam.System.service.impl;

import com.example.Exam.System.dto.student.*;
import com.example.Exam.System.entity.Exam;
import com.example.Exam.System.entity.Questions;
import com.example.Exam.System.entity.Result;
import com.example.Exam.System.entity.User;
import com.example.Exam.System.exception.ResourceNotFoundException;
import com.example.Exam.System.repository.ExamRepo;
import com.example.Exam.System.repository.QuestionsRepo;
import com.example.Exam.System.repository.ResultRepo;
import com.example.Exam.System.repository.UserRepo;
import com.example.Exam.System.security.AuthUtil;
import com.example.Exam.System.service.StudentService;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;


@Service
public class StudentServiceImpl implements StudentService {


    private final ExamRepo examRepo;
    private final ResultRepo resultRepo;
    private final UserRepo userRepo;
    private final QuestionsRepo questionsRepo;
    private ModelMapper modelMapper;
    private AuthUtil authUtil;


    @Autowired
    public StudentServiceImpl(ExamRepo examRepo, ResultRepo resultRepo, UserRepo userRepo, QuestionsRepo questionsRepo) {
        this.examRepo = examRepo;
        this.resultRepo = resultRepo;
        this.userRepo = userRepo;
        this.questionsRepo = questionsRepo;
    }

    @Autowired
    public void setModelMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Autowired
    public void setAuthUtil(AuthUtil authUtil) {
        this.authUtil = authUtil;
    }

    @Override
    public List<BasicExamDetailsResponseDto> findActiveExams() {
        List<Exam> exams =examRepo.findAllActiveExams();
        if(exams.isEmpty())
            throw new ResourceNotFoundException("No Active Exam");
        return exams.stream()
                .map(exam -> modelMapper.map(exam, BasicExamDetailsResponseDto.class))
                .toList();
    }

    @Override
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
    public UserResultResponseDto fetchResult(String token) {

        User user = userRepo.findById(authUtil.getUserIdFromToken(token)).orElseThrow(()-> new ResourceNotFoundException("No User with this Id"));
        UserResultResponseDto userResultDto = modelMapper.map(user, UserResultResponseDto.class);
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
    public Result submitExam(CreateExamRequestDto createExamRequestDto) {

        int correct_answer = 0, wrong_answer = 0;
        for (QuestionResponseDto question : createExamRequestDto.getQuestions()) {
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
                .exams(examRepo.findById(createExamRequestDto.getExam_id()).orElse(null))
                .user(userRepo.findById(createExamRequestDto.getUser_id()).orElse(null))
                .build();
        return resultRepo.save(rs);


    }
}
