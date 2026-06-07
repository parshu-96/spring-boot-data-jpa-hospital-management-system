package com.codingshuttle.youtube.hospitalManagement.security;

import com.codingshuttle.youtube.hospitalManagement.dto.LoginRequestDTO;
import com.codingshuttle.youtube.hospitalManagement.dto.LoginResponseDTO;
import com.codingshuttle.youtube.hospitalManagement.dto.SignupResponseDTO;
import com.codingshuttle.youtube.hospitalManagement.entity.User;
import com.codingshuttle.youtube.hospitalManagement.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final AuthUtil authUtil;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public LoginResponseDTO login(LoginRequestDTO loginRequestDto) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequestDto.getUserName(), loginRequestDto.getPassword())
        );
        User user = (User) authentication.getPrincipal();
        String token = authUtil.generateAccessToken(user);
        return new LoginResponseDTO(token, user.getId());
    }

    public SignupResponseDTO signup(LoginRequestDTO signupRequestDto) {
        User user = userRepository.findByUsername(signupRequestDto.getUserName()).orElse(null);
        if (user != null) {
            throw new IllegalArgumentException("Username already exists");
        }

        user = userRepository.save(User.builder()
                .username(signupRequestDto.getUserName())
                .password(passwordEncoder.encode(signupRequestDto.getPassword()))
                .build());
        return new SignupResponseDTO(user.getId(), user.getUsername());
    }
}
