package com.barbosa.wolfChat.utils.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@Builder
public class ResponseUtil {

    private String message;
    private HttpStatus status;
    private Object data;
    private String sendDateTime;

    public ResponseUtil(){}
    public ResponseUtil(String message, HttpStatus status, Object data, String sendDateTime) {
        this.message = message;
        this.status = status;
        this.data = data;
        this.sendDateTime = sendDateTime;
    }
}
