package com.example.Exam.System.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class QuestionDto {


    private int id;
    private String name;
    private List<QuestionOptionDto> options;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<QuestionOptionDto> getOptions() {
        return options;
    }

    public void setOptions(List<QuestionOptionDto> options) {
        this.options = options;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}

