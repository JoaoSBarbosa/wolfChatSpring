package com.barbosa.wolfChat.core.services.token.providers.jjwt;

import java.time.Instant;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.barbosa.wolfChat.core.services.token.exceptions.JjwtTokenException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import com.barbosa.wolfChat.core.models.security.UserDetailsImpl;
import com.barbosa.wolfChat.core.services.token.interfaces.TokenService;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JjwtTokenService implements TokenService{

	private final JjwtConfigProperties properties;

	@Override
	public String generateAccessToken(UserDetailsImpl user) {
		return generateToken(user, properties.getAccessTokenExpirationInSeconds(), properties.getAccessTokenSigningKey());
	}

	@Override
	public String getSubjectFromAccessToken(String token) {
		return getClaims(token, properties.getAccessTokenSigningKey()).getSubject();
	}

	@Override
	public String generateRefreshToken(UserDetailsImpl user) {
		return generateToken(user, properties.getRefreshTokenExpirationInSeconds(), properties.getRefreshTokenSigningKey());
	}

	@Override
	public String getSubjectFromRefreshToken(String refreshToken) {
		return getClaims(refreshToken, properties.getRefreshTokenSigningKey()).getSubject();
	}
	

	public String generateToken(UserDetailsImpl user, Long expirationInSeconds, String signingKey) {
		var today = Instant.now();
		var expirationDate = today.plusSeconds(expirationInSeconds);
		
		return Jwts.builder()
				.setExpiration(Date.from(expirationDate))
				.setIssuedAt(Date.from(today))
				.setSubject(user.getUsername())
				.signWith(Keys.hmacShaKeyFor(signingKey.getBytes()))
				.setClaims(createClaimsAuth(user))
				.compact();
		
	}

	public static Map<String, Object> createClaimsAuth(UserDetailsImpl user){
		Map<String,Object> claims = new HashMap<>();
		claims.put("roles", user.getAuthorities()
				.stream()
				.map(GrantedAuthority::getAuthority).toList());


		claims.put("firstName", user.getUser().getFirstName());
		claims.put("lastName", user.getUser().getLastName());
		claims.put("urlImage", user.getUser().getImageUri());
		claims.put("dropboxImg",user.getUser().getLinkDropboxImage());
		return claims;
		
	}
	

	public Claims getClaims(String token,String signingKey){
		try {
			return tryGetClaims(token,signingKey);
		}catch (JwtException e){
			throw new JjwtTokenException("Ocorreu um erro ao tentar obter o usairo logado: " + e.getMessage());
		}
	}

	public Claims tryGetClaims(String token,String signingKey){
		return Jwts.parserBuilder()
				.setSigningKey(Keys.hmacShaKeyFor(signingKey.getBytes()))
				.build()
				.parseClaimsJws(token)
				.getBody();
	}

}
