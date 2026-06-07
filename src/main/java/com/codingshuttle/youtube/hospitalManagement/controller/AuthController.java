package com.codingshuttle.youtube.hospitalManagement.controller;

import com.codingshuttle.youtube.hospitalManagement.dto.LoginRequestDTO;
import com.codingshuttle.youtube.hospitalManagement.dto.LoginResponseDTO;
import com.codingshuttle.youtube.hospitalManagement.dto.SignupResponseDTO;
import com.codingshuttle.youtube.hospitalManagement.security.AuthService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@AllArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginRequestDTO loginRequestDto) {
        return ResponseEntity.ok(authService.login(loginRequestDto));
    }

    @PostMapping("/signup")
    public ResponseEntity<SignupResponseDTO> signup(@RequestBody LoginRequestDTO signupRequestDto) {
        return ResponseEntity.ok(authService.signup(signupRequestDto));
    }
}
