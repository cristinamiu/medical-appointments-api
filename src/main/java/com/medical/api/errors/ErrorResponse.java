package com.medical.api.errors;

import lombok.Data;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
public class ErrorResponse {
    private HttpStatus status;

    private LocalDateTime timestamp;
    private String message;

    public ErrorResponse() {
        this.timestamp = LocalDateTime.now();
    }

    public ErrorResponse(HttpStatus status, String message) {
        this();
        this.status = status;
        this.message += message;
    }
}
