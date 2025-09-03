package com.example.Exam.System.controller;


import com.example.Exam.System.dto.LoginRequestDto;
import com.example.Exam.System.dto.LoginResponseDto;
import com.example.Exam.System.dto.SignUpRequestDto;
import com.example.Exam.System.dto.SignUpResponseDto;
import com.example.Exam.System.security.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

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
    public ResponseEntity<SignUpResponseDto> signUp(@RequestBody SignUpRequestDto signUpRequestDto)
    {
        return ResponseEntity.ok(authService.signUp(signUpRequestDto));
    }
}
