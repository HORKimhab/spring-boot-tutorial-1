package com.hkimhab.basic.dto;

import com.hkimhab.basic.validation.UniqueEmail;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record StudentDto(
        @NotBlank @Schema(example = "H", description = "First name") String firstName,
        @NotBlank @Schema(example = "Kimhab", description = "Last name") String lastName,
        @Email @UniqueEmail @Schema(example = "hkimhab@gmail.com", description = "Unique email address") String email,
        @Schema(example = "22", description = "User age") Integer age,
        @Schema(example = "password12345", description = "User age") String password,
        @Schema(example = "1", description = "School ID this user belongs to") Integer schoolId) {

}
