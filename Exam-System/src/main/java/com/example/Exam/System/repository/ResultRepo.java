package com.example.Exam.System.repository;

import com.example.Exam.System.entity.Result;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ResultRepo extends JpaRepository<Result,Integer> {
    @Query(value = "select * from result where id=:id",nativeQuery = true)
    List<Result> FetchResultForOne(@Param("int id") int id);
}
