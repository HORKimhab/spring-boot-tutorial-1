package com.hkimhab.basic.response;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ApiResponseCustomize<T> {

    private int status;
    private String message;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private T data;
}
