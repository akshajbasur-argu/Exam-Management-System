package com.example.Exam.System.dto;

import com.example.Exam.System.entity.Exam;
import com.example.Exam.System.entity.QuestionOption;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;
@Data
public class QuestionWithAnswerDto {

    private String name;
    private String questionOption;
    private List<QuestionOptionDto> options;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getQuestionOption() {
        return questionOption;
    }

    public void setQuestionOption(String questionOption) {
        this.questionOption = questionOption;
    }

    public List<QuestionOptionDto> getOptions() {
        return options;
    }

    public void setOptions(List<QuestionOptionDto> options) {
        this.options = options;
    }
}
