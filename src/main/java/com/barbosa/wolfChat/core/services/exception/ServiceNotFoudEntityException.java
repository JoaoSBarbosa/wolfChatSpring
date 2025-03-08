package com.barbosa.wolfChat.core.services.exception;

public class ServiceNotFoudEntityException extends RuntimeException {

    private static final long serialVersionUID = 1L;

	public ServiceNotFoudEntityException(String message) {
        super(message);
    }
}
