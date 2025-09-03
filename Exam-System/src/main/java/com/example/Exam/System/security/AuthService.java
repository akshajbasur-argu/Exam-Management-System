package com.example.Exam.System.security;


import com.example.Exam.System.dto.LoginRequestDto;
import com.example.Exam.System.dto.LoginResponseDto;
import com.example.Exam.System.dto.SignUpRequestDto;
import com.example.Exam.System.dto.SignUpResponseDto;
import com.example.Exam.System.entity.User;
import com.example.Exam.System.repository.UserRepo;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.net.Authenticator;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final AuthenticationManager authenticationManager;
    private final AuthUtil authUtil;
    private final UserRepo userRepo;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;
    public LoginResponseDto login(LoginRequestDto loginRequestDto) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequestDto.getUsername(),loginRequestDto.getPassword())
        );
        User user =(User) authentication.getPrincipal();
        String token=authUtil.generateAccessToken(user);
        return new LoginResponseDto(token, user.getId());
    }

    public SignUpResponseDto signUp(SignUpRequestDto signUpRequestDto) {
        User user= userRepo.findByUsername(signUpRequestDto.getUsername()).orElse(null);
        if(user!=null)
            throw new IllegalArgumentException("User Already Exists");
        user=userRepo.save(User.builder()
                .username(signUpRequestDto.getUsername())
                        .password(passwordEncoder.encode(signUpRequestDto.getPassword()))
                        .first_name(signUpRequestDto.getFirst_name())
                        .last_name(signUpRequestDto.getLast_name())
                        .role(signUpRequestDto.getRole())
                .build()
        );
        return modelMapper.map(user,SignUpResponseDto.class);
    }
}
