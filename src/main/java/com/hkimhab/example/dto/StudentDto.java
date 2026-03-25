package com.hkimhab.example.dto;

import io.swagger.v3.oas.annotations.media.Schema;

public record StudentDto(
        @Schema(example = "H", description = "First name") String firstName,
        @Schema(example = "Kimhab", description = "Last name") String lastName,
        @Schema(example = "hkimhab@gmail.com", description = "Unique email address") String email,
        @Schema(example = "22", description = "User age") Integer age,
        @Schema(example = "password12345", description = "User age") String password,
        @Schema(example = "1", description = "School ID this user belongs to") Integer schoolId) {

}
