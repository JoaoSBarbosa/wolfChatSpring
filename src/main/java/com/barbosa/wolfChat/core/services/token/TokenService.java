package com.barbosa.wolfChat.core.services.token;

import com.barbosa.wolfChat.core.models.security.UserDetailsImpl;

public interface TokenService {
	
	String generateAccessToken(UserDetailsImpl user);
	String getSubjectFromAccessToken(String token);
	String generateRefreshToken(UserDetailsImpl user);
    String getSubjectFromRefreshToken(String refreshToken);

}
