package com.hkimhab.basic.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.Data;

@Data
@JsonPropertyOrder({ "status", "message", "data", "error" })
public class ApiResponseCustomize<T> {

    private int status;
    private String message;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private T data;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Object error;

    public ApiResponseCustomize(int status, String message, T data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public ApiResponseCustomize(int status, String message, T data, Object error) {
        this.status = status;
        this.message = message;
        this.data = data;
        this.error = error;
    }
}
