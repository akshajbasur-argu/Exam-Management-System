package com.example.Exam.System.dto.student;

import java.util.List;

public class UserResultResponseDto {
    private String first_name;
    private String last_name;
    private List<ResultDto> result;

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public List<ResultDto> getResult() {
        return result;
    }

    public void setResult(List<ResultDto> result) {
        this.result = result;
    }
}
