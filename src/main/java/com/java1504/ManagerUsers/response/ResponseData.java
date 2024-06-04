package com.java1504.ManagerUsers.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
public class ResponseData<T> {
    private final int status;
    private final String message;
    private final LocalDateTime localDateTime;

    private T data;

    public ResponseData(int status, String message, LocalDateTime localDateTime) {
        this.status = status;

        this.localDateTime = localDateTime;
        int start = message.indexOf("='");
        int end = message.lastIndexOf("',");
        message = message.substring(start +2,end);
        this.message = message;
    }

    public ResponseData(int status, String message, LocalDateTime localDateTime, T data) {
        this.status = status;
        this.message = message;
        this.localDateTime = localDateTime;
        this.data = data;
    }


}