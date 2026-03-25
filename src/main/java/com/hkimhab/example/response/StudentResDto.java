package com.hkimhab.example.response;

public record StudentResDto(
        String firstName,
        String lastName,
        String email,
        Integer age) {
}