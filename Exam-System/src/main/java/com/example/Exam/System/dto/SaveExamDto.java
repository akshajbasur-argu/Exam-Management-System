package com.example.Exam.System.dto;

import com.example.Exam.System.entity.Questions;
import jakarta.persistence.CascadeType;
import jakarta.persistence.OneToMany;
import lombok.Data;

import java.util.List;

@Data

public class SaveExamDto {
    private String name;
    private String topic;
    private boolean active;
    private int duration;
    private int created_by ;
    private List<QuestionWithAnswerDto> questions;

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

    public int getCreated_by() {
        return created_by;
    }

    public void setCreated_by(int created_by) {
        this.created_by = created_by;
    }

    public List<QuestionWithAnswerDto> getQuestions() {
        return questions;
    }

    public void setQuestions(List<QuestionWithAnswerDto> questions) {
        this.questions = questions;
    }



}
