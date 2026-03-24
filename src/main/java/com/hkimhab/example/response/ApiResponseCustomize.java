package com.hkimhab.example.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ApiResponseCustomize<T> {

    private int status;
    private String message;
    private T data;
}
