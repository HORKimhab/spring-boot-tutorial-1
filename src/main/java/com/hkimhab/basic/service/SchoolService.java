package com.hkimhab.basic.service;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.hkimhab.basic.dto.SchoolDto;
import com.hkimhab.basic.mapper.SchoolMapper;
import com.hkimhab.basic.model.School;
import com.hkimhab.basic.repository.SchoolRepository;
import com.hkimhab.basic.response.ApiResponseCustomize;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import jakarta.validation.Valid;

@Service
public class SchoolService {

    private final SchoolMapper schoolMapper;
    private final SchoolRepository baseRepo;

    public SchoolService(SchoolMapper schoolMapper, SchoolRepository baseRepo) {
        this.schoolMapper = schoolMapper;
        this.baseRepo = baseRepo;
    }

    public SchoolDto post(
            @Valid @org.springframework.web.bind.annotation.RequestBody @RequestBody(description = "School object to be created", required = true, content = @Content(schema = @Schema(implementation = School.class))) SchoolDto baseDto) {
        var school = schoolMapper.toSchool(baseDto);
        baseRepo.save(school);
        return baseDto;

    }

    public ResponseEntity<?> getAllSchools() {
        // List<School> bases = baseRepo.findAll();
        // return ResponseEntity.ok(
        // new ApiResponseCustomize<>(200, "Schools retrieved successfully", bases));

        List<SchoolDto> schools = baseRepo.findAll().stream()
                .map(s -> new SchoolDto(s.getName()))
                .toList();
        return ResponseEntity.ok(
                new ApiResponseCustomize<>(200, "Schools retrieved successfully", schools));
    }
}
