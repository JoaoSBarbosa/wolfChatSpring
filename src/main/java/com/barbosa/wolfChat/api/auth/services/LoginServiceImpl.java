package com.barbosa.wolfChat.api.auth.services;

import com.barbosa.wolfChat.api.auth.dtos.LoginRequest;
import com.barbosa.wolfChat.api.auth.dtos.LoginResponse;
import com.barbosa.wolfChat.core.models.security.UserDetailsImpl;
import com.barbosa.wolfChat.core.services.token.interfaces.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginServiceImpl implements LoginService {

    private final TokenService tokenService;
    private final AuthenticationManager manager;
    @Override
    public LoginResponse login(LoginRequest loginRequest) {

        var userNameAuthentication = new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword());
        var auth = manager.authenticate(userNameAuthentication);
        var userDetails = (UserDetailsImpl) auth.getPrincipal();

        return LoginResponse.
                builder()
                .token(tokenService.generateAccessToken(userDetails))
                .refreshToken(tokenService.generateRefreshToken(userDetails))
                .build();
    }
}
