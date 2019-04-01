package com.qingteng.demo.error;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public class CustomErrorResponse {

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd hh:mm:ss")
    private LocalDateTime timestamp;
    private int status;
    private String error;

    //...getters setters


    public CustomErrorResponse() {
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public CustomErrorResponse setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
        return this;
    }

    public int getStatus() {
        return status;
    }

    public CustomErrorResponse setStatus(int status) {
        this.status = status;
        return this;
    }

    public String getError() {
        return error;
    }

    public CustomErrorResponse setError(String error) {
        this.error = error;
        return this;
    }
}