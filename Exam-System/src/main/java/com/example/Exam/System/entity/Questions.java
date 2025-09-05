package com.example.Exam.System.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class Questions {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "exam_id")
    private Exam exam;
    /// (FK) done

    @Column(nullable = false)
    private String name;


    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "answer_id")
    private QuestionOption questionOption;
    /// (correct option ID) done

    @OneToMany(mappedBy = "questions", cascade = CascadeType.ALL)
    private List<QuestionOption> options;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Exam getExam() {
        return exam;
    }

    public void setExam(Exam exam) {
        this.exam = exam;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public QuestionOption getQuestionOption() {
        return questionOption;
    }

    public void setQuestionOption(QuestionOption questionOption) {
        this.questionOption = questionOption;
    }

    public List<QuestionOption> getOptions() {
        return options;
    }

    public void setOptions(List<QuestionOption> options) {
        this.options = options;
    }
}

    /// (question text)


