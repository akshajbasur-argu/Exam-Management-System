package com.example.Exam.System.dto;

public class QuestionOptionDto {
    private int id;
    private String name;  ///(option text)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
