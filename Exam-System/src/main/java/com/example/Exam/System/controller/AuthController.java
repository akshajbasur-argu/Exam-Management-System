package com.example.Exam.System.controller;


import com.example.Exam.System.dto.login.LoginRequestDto;
import com.example.Exam.System.dto.login.LoginResponseDto;
import com.example.Exam.System.dto.signup.SignUpRequestDto;
import com.example.Exam.System.dto.signup.SignUpResponseDto;
import com.example.Exam.System.exception.ApiError;
import com.example.Exam.System.service.impl.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.NoHandlerFoundException;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;
    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(@RequestBody LoginRequestDto loginRequestDto)
    {
        return ResponseEntity.ok(authService.login(loginRequestDto));
    }
    @PostMapping("/signUp")
    public ResponseEntity<SignUpResponseDto> signUp(@Valid @RequestBody SignUpRequestDto signUpRequestDto)
    {
        return ResponseEntity.ok(authService.signUp(signUpRequestDto));
    }


}
