package com.hkimhab.basic.controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hkimhab.basic.dto.StudentDto;
import com.hkimhab.basic.mapper.StudentMapper;
import com.hkimhab.basic.model.User;
import com.hkimhab.basic.repository.UserRepository;
import com.hkimhab.basic.response.ApiResponseCustomize;
import com.hkimhab.basic.response.StudentResDto;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api")
@Tag(name = "User API", description = "API for managing users")
public class UserController {

    private final UserRepository userRepo;
    private final StudentMapper studentMapper;

    public UserController(UserRepository userRepo, StudentMapper studentMapper) {
        this.userRepo = userRepo;
        this.studentMapper = studentMapper;
    }

    @PostMapping("/user")
    @Operation(summary = "Create new user", description = "Add a new user to the database")
    public StudentResDto post(
            @Valid @org.springframework.web.bind.annotation.RequestBody @RequestBody(description = "User object to be created", required = true, content = @Content(schema = @Schema(implementation = StudentDto.class))) StudentDto userDto) {
        var user = studentMapper.toStudent(userDto);
        var data = userRepo.save(user);
        return studentMapper.toStudentResDto(data);
    }

    @GetMapping("/user")
    public ResponseEntity<?> getAllUsers() {
        List<User> users = userRepo.findAll();
        return ResponseEntity.ok(
                new ApiResponseCustomize<>(200, "Users retrieved successfully", users));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<?> getUserById(@PathVariable Long userId) {

        User user = userRepo.findById(userId)
                .orElseThrow(() -> new NoSuchElementException("Invalid userId"));

        return ResponseEntity.ok(new ApiResponseCustomize<>(200, "User found", user));
    }

    @GetMapping("/user/search/{name}")
    public ResponseEntity<?> searchUesrName(@PathVariable String name) {

        List<User> users = userRepo.searchByName(name);

        return ResponseEntity.ok(
                new ApiResponseCustomize<>(200, users.isEmpty() ? "None user" : "Search users found", users));
    }

    // soft delete
    @DeleteMapping("/user/{userId}")
    @Operation(summary = "Soft delete user")
    public ResponseEntity<?> softDelete(@PathVariable Long userId) {
        User user = userRepo.findById(userId)
                .orElseThrow(() -> new NoSuchElementException("Soft delete failed: Invalid user"));

        // ← just set deletedAt, don't remove from DB
        user.setDeletedAt(LocalDateTime.now());

        userRepo.save(user);

        return ResponseEntity.ok(new ApiResponseCustomize<>(200, "User soft deleted", null));
    }

    // force delete — permanently removes from DB
    @DeleteMapping("/user/{userId}/force")
    @Operation(summary = "Force delete user permanently")
    public ResponseEntity<?> forceDelete(@PathVariable Long userId) {
        userRepo.findByIdIncludeDeleted(userId)
                .orElseThrow(() -> new NoSuchElementException("Force delete failed: Invalid user"));

        userRepo.forceDeleteById(userId); // ← permanently removes from DB

        return ResponseEntity.ok(new ApiResponseCustomize<>(200, "User permanently deleted", null));
    }
}
