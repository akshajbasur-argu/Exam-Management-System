package com.example.Exam.System.dto.student;

public class QuestionResponseDto {
    private int questions_id;
    private int questionsOptions_id;

    public int getQuestionsOptions_id() {
        return questionsOptions_id;
    }

    public void setQuestionsOptions_id(int questionsOptions_id) {
        this.questionsOptions_id = questionsOptions_id;
    }

    public int getQuestions_id() {
        return questions_id;
    }

    public void setQuestions_id(int questions_id) {
        this.questions_id = questions_id;
    }
}
