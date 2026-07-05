package com.example.pro_ready_features.advice;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalTime;

@Data
public class ApiResponse<T> {
//    @JsonFormat(pattern = "hh:mm:ss dd-MM-yyyy")
    private LocalTime timeStamp;

    private T data;
    private ApiResponse error;

    public ApiResponse() {
        this.data = data;
    }

    public ApiResponse(ApiError timeStamp) {
        this.timeStamp = LocalTime.now();
    }

    public ApiResponse(ApiResponse error) {
        this();
        this.error = error;
    }


}
