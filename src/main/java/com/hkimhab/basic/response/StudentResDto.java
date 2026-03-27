package com.hkimhab.basic.response;

public record StudentResDto(
        String firstName,
        String lastName,
        String email,
        Integer age) {
}