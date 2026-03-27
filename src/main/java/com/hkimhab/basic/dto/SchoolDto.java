package com.hkimhab.basic.dto;

import io.swagger.v3.oas.annotations.media.Schema;

public record SchoolDto(
        @Schema(example = "Rean tech V1") String name) {
}
