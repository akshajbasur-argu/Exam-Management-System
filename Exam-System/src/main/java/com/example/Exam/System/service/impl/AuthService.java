package com.example.Exam.System.service.impl;


import com.example.Exam.System.dto.login.LoginRequestDto;
import com.example.Exam.System.dto.login.LoginResponseDto;
import com.example.Exam.System.dto.signup.SignUpRequestDto;
import com.example.Exam.System.dto.signup.SignUpResponseDto;
import com.example.Exam.System.entity.User;
import com.example.Exam.System.repository.UserRepo;
import com.example.Exam.System.security.AuthUtil;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private final AuthenticationManager authenticationManager;
    private AuthUtil authUtil;
    private final UserRepo userRepo;
    private ModelMapper modelMapper;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public void setAuthUtil(AuthUtil authUtil) {
        this.authUtil= authUtil;
    }

    @Autowired
    public void setModelMapper(ModelMapper modelMapper) {
        this.modelMapper= modelMapper;
    }

    @Autowired
    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder=passwordEncoder;
    }

    @Autowired
    public AuthService(AuthenticationManager authenticationManager, UserRepo userRepo) {
        this.authenticationManager = authenticationManager;
        this.userRepo = userRepo;
    }

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
