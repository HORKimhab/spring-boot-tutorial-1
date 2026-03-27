package com.hkimhab.basic.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.hkimhab.basic.dto.StudentDto;
import com.hkimhab.basic.mapper.StudentMapper;
import com.hkimhab.basic.model.User;
import com.hkimhab.basic.repository.UserRepository;
import com.hkimhab.basic.response.ApiResponseCustomize;
import com.hkimhab.basic.response.StudentResDto;

@Service
public class StudentService {

    private final UserRepository userRepo;
    private final StudentMapper studentMapper;

    public StudentService(UserRepository userRepo, StudentMapper studentMapper) {
        this.userRepo = userRepo;
        this.studentMapper = studentMapper;
    }

    public StudentResDto saveStudent(
            StudentDto userDto) {
        var user = studentMapper.toStudent(userDto);
        var data = userRepo.save(user);
        return studentMapper.toStudentResDto(data);
    }

    public ResponseEntity<?> getAllUsers() {
        List<User> users = userRepo.findAll();
        return ResponseEntity.ok(
                new ApiResponseCustomize<>(200, "Users retrieved successfully", users));
    }

    public ResponseEntity<?> getUserById(@PathVariable Long userId) {
        User user = userRepo.findById(userId)
                .orElseThrow(() -> new NoSuchElementException("Invalid userId"));

        return ResponseEntity.ok(new ApiResponseCustomize<>(200, "User found", user));
    }

    public ResponseEntity<?> searchUesrName(@PathVariable String name) {
        List<User> users = userRepo.searchByName(name);
        return ResponseEntity.ok(
                new ApiResponseCustomize<>(200, users.isEmpty() ? "None user" : "Search users found", users));
    }

    public ResponseEntity<?> softDelete(@PathVariable Long userId) {
        User user = userRepo.findById(userId)
                .orElseThrow(() -> new NoSuchElementException("Soft delete failed: Invalid user"));

        // ← just set deletedAt, don't remove from DB
        user.setDeletedAt(LocalDateTime.now());

        userRepo.save(user);

        return ResponseEntity.ok(new ApiResponseCustomize<>(200, "User soft deleted", null));
    }

    public ResponseEntity<?> forceDelete(@PathVariable Long userId) {
        userRepo.findByIdIncludeDeleted(userId)
                .orElseThrow(() -> new NoSuchElementException("Force delete failed: Invalid user"));

        userRepo.forceDeleteById(userId); // ← permanently removes from DB

        return ResponseEntity.ok(new ApiResponseCustomize<>(200, "User permanently deleted", null));
    }
}
