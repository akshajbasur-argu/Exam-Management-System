package com.example.Exam.System.repository;

import com.example.Exam.System.entity.QuestionOption;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface QuestionOptionRepo extends JpaRepository<QuestionOption,Integer> {
}
