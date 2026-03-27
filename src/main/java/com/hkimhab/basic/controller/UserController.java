package com.hkimhab.basic.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hkimhab.basic.dto.StudentDto;
import com.hkimhab.basic.response.StudentResDto;
import com.hkimhab.basic.service.StudentService;

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

    private final StudentService baseService;

    public UserController(StudentService baseService) {
        this.baseService = baseService;
    }

    @PostMapping("/user")
    @Operation(summary = "Create new user", description = "Add a new user to the database")
    public StudentResDto saveStudent(
            @Valid @org.springframework.web.bind.annotation.RequestBody @RequestBody(description = "User object to be created", required = true, content = @Content(schema = @Schema(implementation = StudentDto.class))) StudentDto userDto) {
        return baseService.saveStudent(userDto);
    }

    @GetMapping("/user")
    public ResponseEntity<?> getAllUsers() {
        return baseService.getAllUsers();
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<?> getUserById(@PathVariable Long userId) {
        return baseService.getUserById(userId);
    }

    @GetMapping("/user/search/{name}")
    public ResponseEntity<?> searchUesrName(@PathVariable String name) {
        return baseService.searchUesrName(name);
    }

    // soft delete
    @DeleteMapping("/user/{userId}")
    @Operation(summary = "Soft delete user")
    public ResponseEntity<?> softDelete(@PathVariable Long userId) {
        return baseService.softDelete(userId);
    }

    // force delete — permanently removes from DB
    @DeleteMapping("/user/{userId}/force")
    @Operation(summary = "Force delete user permanently")
    public ResponseEntity<?> forceDelete(@PathVariable Long userId) {
        return baseService.forceDelete(userId);
    }
}
