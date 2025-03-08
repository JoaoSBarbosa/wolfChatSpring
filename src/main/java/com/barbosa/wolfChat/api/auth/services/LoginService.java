package com.barbosa.wolfChat.api.auth.services;

import com.barbosa.wolfChat.api.auth.dtos.LoginRequest;
import com.barbosa.wolfChat.api.auth.dtos.LoginResponse;

public interface LoginService {

    LoginResponse login(LoginRequest loginRequest);
}
