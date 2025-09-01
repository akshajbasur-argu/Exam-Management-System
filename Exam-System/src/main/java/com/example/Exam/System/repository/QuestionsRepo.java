package com.example.Exam.System.repository;

import com.example.Exam.System.entity.Questions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository

public interface QuestionsRepo extends JpaRepository<Questions,Integer> {
}
