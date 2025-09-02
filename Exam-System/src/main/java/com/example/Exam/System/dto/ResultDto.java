package com.example.Exam.System.dto;

public class ResultDto {
    private int obtained_mark;
    private int no_of_right_answer;
    private int no_of_wrong_answer;
    private BasicExamResultDetailsDto exam;

    public int getObtained_mark() {
        return obtained_mark;
    }

    public void setObtained_mark(int obtained_mark) {
        this.obtained_mark = obtained_mark;
    }

    public int getNo_of_right_answer() {
        return no_of_right_answer;
    }

    public void setNo_of_right_answer(int no_of_right_answer) {
        this.no_of_right_answer = no_of_right_answer;
    }

    public int getNo_of_wrong_answer() {
        return no_of_wrong_answer;
    }

    public void setNo_of_wrong_answer(int no_of_wrong_answer) {
        this.no_of_wrong_answer = no_of_wrong_answer;
    }

    public BasicExamResultDetailsDto getExam() {
        return exam;
    }

    public void setExam(BasicExamResultDetailsDto exam) {
        this.exam = exam;
    }

}
