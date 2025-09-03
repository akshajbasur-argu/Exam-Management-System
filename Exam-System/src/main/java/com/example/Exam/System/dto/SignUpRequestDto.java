package com.example.Exam.System.dto;

import com.example.Exam.System.entity.Result;
import com.example.Exam.System.entity.UserRole;
import jakarta.persistence.CascadeType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SignUpRequestDto {
    private String username;
    private String password;
    private String first_name;
    private String last_name;

    @Enumerated(EnumType.STRING)
    private UserRole role;

}
