package com.example.Exam.System.service.impl;

import com.example.Exam.System.dto.QuestionOptionDto;
import com.example.Exam.System.dto.professor.AddQuestionsRequestDto;
import com.example.Exam.System.dto.professor.CreateExamRequestDto;
import com.example.Exam.System.entity.*;
import com.example.Exam.System.repository.ExamRepo;
import com.example.Exam.System.repository.QuestionsRepo;
import com.example.Exam.System.repository.UserRepo;
import com.example.Exam.System.security.AuthUtil;
import com.example.Exam.System.service.ProfessorService;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProfessorServiceImpl implements ProfessorService {

    @Autowired
    private ExamRepo examRepo;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private QuestionsRepo questionsRepo;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private AuthUtil authUtil;


    @Override
    public Exam createExam(CreateExamRequestDto examDto, String token) {
        int userId=authUtil.getUserIdFromToken(token);
        Exam exam = modelMapper.map(examDto,Exam.class);
        exam.setCreated_by(userId);
        List<Questions> questions= new ArrayList<>();
        for(AddQuestionsRequestDto question: examDto.getQuestions())
        {
            Questions questions1 = new Questions();
            questions1.setExam(exam);
            questions1.setName(question.getName());

            List<QuestionOption> questionOptions = new ArrayList<>();
            QuestionOption correctOption=null;
            for(QuestionOptionDto text : question.getOptions())
            {
                QuestionOption questionOption;
                questionOption = new QuestionOption();
                questionOption.setQuestions(questions1);
                questionOption.setName(text.getName());

                if(text.getName().equals(question.getQuestionOption()))
                    correctOption=questionOption;
                questionOptions.add(questionOption);
            }
            questions1.setOptions(questionOptions);
            questions1.setQuestionOption(correctOption);

            questions.add(questions1);
        }
        exam.setQuestions(questions);
        return examRepo.save(exam);
        }

    @Override
    @Transactional
    public Questions addQuestions(int id , AddQuestionsRequestDto questions) {

        Exam exam = examRepo.findById(id).orElseThrow(()-> new IllegalArgumentException("Exam does not exists"));




        Questions question = modelMapper.map(questions,Questions.class);

        List<QuestionOption> questionOptions = new ArrayList<>();
        QuestionOption correctOption=null;

        for(QuestionOption text : question.getOptions())
        {
            QuestionOption questionOption;
            questionOption = new QuestionOption();
            questionOption.setQuestions(question);
            questionOption.setName(text.getName());

            questionOptions.add(questionOption);
            if(text.getName().equals(questions.getQuestionOption())) {
                correctOption=text;
            }
        }
        question.setOptions(questionOptions);
        question.setQuestionOption(correctOption);

        List<Questions> questionsList = exam.getQuestions();
        questionsList.add(question);
        exam.setQuestions(questionsList);
        question.setExam(exam);


        return questionsRepo.save(question);

    }

    @Override
    public Exam examStatus(Exam exam) {
        exam.setActive(exam.isActive()?false:true);
        return examRepo.save(exam);
    }

    @Override
    public List<Result> viewAllResults(int id) {
        Exam exam = examRepo.findById(id).orElseThrow();
        return exam.getResults();
    }
}
