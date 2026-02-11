package com.yash.TodoBackend.controller;

import com.yash.TodoBackend.dto.AuthRequest;
import com.yash.TodoBackend.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    private AuthService service;

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody AuthRequest req)
    {
        String token = service.register(req);
        return ResponseEntity.status(HttpStatus.CREATED).body(token) ;
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@Valid @RequestBody AuthRequest req){
       return new ResponseEntity<>(service.login(req), HttpStatus.CREATED);
    }
}
