package com.yash.TodoBackend.service;

import com.yash.TodoBackend.dto.AuthRequest;
import com.yash.TodoBackend.entity.User;
import com.yash.TodoBackend.repositories.UserRepository;
import com.yash.TodoBackend.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class AuthService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtUtil jwtUtil;

    public String register(AuthRequest req){
        if(userRepository.findByEmail(req.getEmail()).isPresent()){
            throw new ResponseStatusException(HttpStatus.CONFLICT,"User already exists");
        }
        User user = new User();
        user.setEmail(req.getEmail());
        user.setPassword(passwordEncoder.encode(req.getPassword()));
        userRepository.save(user);
        return jwtUtil.generateToken(user.getEmail());
    }

    public String login(AuthRequest req){
        User user = userRepository.findByEmail(req.getEmail()).orElseThrow(
                ()-> new RuntimeException("Invalid User!")
        );

        if(!passwordEncoder.matches(req.getPassword(), user.getPassword())){
            throw new RuntimeException("Invalid Password!");
        }
        return jwtUtil.generateToken(req.getEmail());
    }
}
