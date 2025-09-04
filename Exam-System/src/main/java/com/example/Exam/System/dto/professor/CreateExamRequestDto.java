package com.example.Exam.System.dto.professor;

import lombok.Data;

import java.util.List;

@Data

public class CreateExamRequestDto {
    private String name;
    private String topic;
    private boolean active;
    private int duration;
    private List<AddQuestionsRequestDto> questions;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public List<AddQuestionsRequestDto> getQuestions() {
        return questions;
    }

    public void setQuestions(List<AddQuestionsRequestDto> questions) {
        this.questions = questions;
    }



}
