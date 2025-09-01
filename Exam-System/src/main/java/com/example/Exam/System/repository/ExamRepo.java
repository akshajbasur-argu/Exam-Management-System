package com.example.Exam.System.repository;

import com.example.Exam.System.entity.Exam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExamRepo extends JpaRepository<Exam,Integer> {
    @Query(value = "select * from exam where active=true", nativeQuery = true)
    List<Exam> findAllActiveExams();

}
