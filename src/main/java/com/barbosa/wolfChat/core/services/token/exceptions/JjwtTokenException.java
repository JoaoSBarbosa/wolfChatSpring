package com.barbosa.wolfChat.core.services.token.exceptions;

public class JjwtTokenException extends RuntimeException {
    public JjwtTokenException(String message) {
        super(message);
    }
}
