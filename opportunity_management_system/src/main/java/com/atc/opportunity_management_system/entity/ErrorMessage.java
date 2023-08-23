package com.atc.opportunity_management_system.entity;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class ErrorMessage {
    private Object message;
    private long time;
    private int status;
    public ErrorMessage(Object message, int status) {
        this.message = message;
        this.status = status;
        time = System.currentTimeMillis();
    }

    
}
