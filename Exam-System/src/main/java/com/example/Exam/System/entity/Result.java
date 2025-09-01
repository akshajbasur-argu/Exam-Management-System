package com.example.Exam.System.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Result {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user; /// (FK) done

    @ManyToOne
    @JoinColumn(name = "exam_id")
    private Exam exams;  ///(FK) done

    private int obtained_mark;
    private int no_of_right_answer;
    private int no_of_wrong_answer;
}
