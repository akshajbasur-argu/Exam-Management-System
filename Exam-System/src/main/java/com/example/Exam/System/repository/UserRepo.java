package com.example.Exam.System.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.Exam.System.entity.User;

@Repository
public interface UserRepo extends JpaRepository<User,Integer> {
}
