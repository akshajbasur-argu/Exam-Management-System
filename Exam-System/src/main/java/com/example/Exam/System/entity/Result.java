package com.example.Exam.System.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Getter
@Setter
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

    @Column(nullable = false)
    private int obtained_mark;

    @Column(nullable = false)
    private int no_of_right_answer;

    @Column(nullable = false)
    private int no_of_wrong_answer;


}
