package com.barbosa.wolfChat.controllers.exception;

public class TokenServiceExeption extends RuntimeException {
    public TokenServiceExeption(String message) {
        super(message);
    }
}
