package com.barbosa.wolfChat.utils.model;

import lombok.Builder;
import lombok.Data;

import java.time.Instant;

@Data
@Builder
public class StandardError {

    private Instant timestamp;
    private Integer status;
    private String error;
    private String message;
    private String path;

    public StandardError(Instant timestamp, Integer status, String error, String message, String path) {}
    public StandardError() {}

}
