package com.example.ERP.System.HR.ExceptionHandling;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
class ApiError {
    private String msg;
    private int status;
    private LocalDateTime timestamp;
    private List<String> errors;

    public ApiError(String msg,int status) {
        this.msg = msg;
        this.status = status;
        this.timestamp = LocalDateTime.now();}

    //for custom message
    public ApiError(String msg, int status, List<String> errors) {
        this.msg = msg;
        this.status = status;
        this.timestamp = LocalDateTime.now();
        this.errors = errors;
    }

}
