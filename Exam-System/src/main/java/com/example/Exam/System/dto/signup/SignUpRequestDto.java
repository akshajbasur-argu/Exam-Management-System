package com.example.Exam.System.dto.signup;

import com.example.Exam.System.entity.UserRole;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SignUpRequestDto {
    private String username;

    @NotBlank(message = "Password Cannot Be Empty")
    @Size(min=8,max=20,message = "Password must be between 8 and 10 characters")
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!]).*$",

            message = "Password must contain at least one digit, one lowercase, one uppercase, and one special character"
    )
    private String password;
    private String first_name;
    private String last_name;

    @Enumerated(EnumType.STRING)
    private UserRole role;

}
