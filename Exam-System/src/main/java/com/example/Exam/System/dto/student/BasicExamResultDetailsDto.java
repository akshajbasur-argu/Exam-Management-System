package com.example.Exam.System.dto.student;

public class BasicExamResultDetailsDto {
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

    private String name;
    private String topic;
}
