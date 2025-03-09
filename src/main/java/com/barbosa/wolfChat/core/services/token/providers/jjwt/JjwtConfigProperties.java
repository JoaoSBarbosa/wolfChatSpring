package com.barbosa.wolfChat.core.services.token.providers.jjwt;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "com.barbosa.wolfchat.core.services.token.jjwt")
public class JjwtConfigProperties {

    private String accessTokenSigningKey;
    private Long accessTokenExpirationInSeconds;
    private String refreshTokenSigningKey;
    private Long refreshTokenExpirationInSeconds;
}
