package com.barbosa.wolfChat.api.auth.controllers;


import com.barbosa.wolfChat.api.auth.dtos.LoginRequest;
import com.barbosa.wolfChat.api.auth.dtos.LoginResponse;
import com.barbosa.wolfChat.api.auth.services.LoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/login")
public class LoginController {

    private final LoginService loginService;

    @PostMapping
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest) {
        var response = loginService.login(loginRequest);
        return ResponseEntity.ok(response);
    }
}
