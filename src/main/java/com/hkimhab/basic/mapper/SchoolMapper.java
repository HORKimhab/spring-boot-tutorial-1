package com.hkimhab.basic.mapper;

import org.springframework.stereotype.Service;

import com.hkimhab.basic.dto.SchoolDto;
import com.hkimhab.basic.model.School;

@Service
public class SchoolMapper {
    public School toSchool(SchoolDto dto) {
        return new School(dto.name());
    }

    public SchoolDto toSchoolDto(School school) {
        return new SchoolDto(school.getName());
    }

}
