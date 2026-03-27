package com.hkimhab.basic.mapper;

import org.springframework.stereotype.Service;

import com.hkimhab.basic.dto.StudentDto;
import com.hkimhab.basic.model.School;
import com.hkimhab.basic.model.User;
import com.hkimhab.basic.response.StudentResDto;

@Service
public class StudentMapper {

    public User toStudent(StudentDto dto) {
        var student = new User();
        student.setFirstName(dto.firstName());
        student.setLastName(dto.lastName());
        student.setEmail(dto.email());
        student.setAge(dto.age());
        student.setPassword(dto.password());

        var school = new School();
        school.setId(dto.schoolId());
        student.setSchool(school);
        return student;
    }

    public StudentResDto toStudentResDto(User user) {
        return new StudentResDto(
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                user.getAge());
    }
}
