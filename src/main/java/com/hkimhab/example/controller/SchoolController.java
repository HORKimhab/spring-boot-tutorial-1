package com.hkimhab.example.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hkimhab.example.model.School;
import com.hkimhab.example.repository.SchoolRepository;
import com.hkimhab.example.response.ApiResponseCustomize;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api")
@Tag(name = "School API", description = "API for managing schools")
public class SchoolController {

    private final SchoolRepository baseRepo;

    public SchoolController(SchoolRepository baseRepo) {
        this.baseRepo = baseRepo;
    }

    @PostMapping("/school")
    @Operation(summary = "Create new school", description = "Add a new school to the database")
    public School post(
            @Valid
            @org.springframework.web.bind.annotation.RequestBody
            @RequestBody(
                    description = "School object to be created",
                    required = true,
                    content = @Content(schema = @Schema(implementation = School.class))) School school
    ) {
        System.out.println(school);
        return baseRepo.save(school);
    }

    @GetMapping("/school")
    public ResponseEntity<?> getAllSchools() {
        List<School> bases = baseRepo.findAll();
        return ResponseEntity.ok(
                new ApiResponseCustomize<>(200, "Schools retrieved successfully", bases)
        );
    }

    // @GetMapping("/user/{userId}")
    // public ResponseEntity<?> getUserById(@PathVariable Long userId) {
    //     User user = userRepo.findById(userId)
    //             .orElseThrow(() -> new NoSuchElementException("Invalid userId"));
    //     return ResponseEntity.ok(new ApiResponseCustomize<>(200, "User found", user));
    // }
    // @GetMapping("/user/search/{name}")
    // public ResponseEntity<?> searchUesrName(@PathVariable String name) {
    //     List<User> users = userRepo.searchByName(name);
    //     return ResponseEntity.ok(
    //             new ApiResponseCustomize<>(200, users.isEmpty() ? "None user" : "Search users found", users)
    //     );
    // }
    // // soft delete
    // @DeleteMapping("/user/{userId}")
    // @Operation(summary = "Soft delete user")
    // public ResponseEntity<?> softDelete(@PathVariable Long userId) {
    //     User user = userRepo.findById(userId)
    //             .orElseThrow(() -> new NoSuchElementException("Soft delete failed: Invalid user"));
    //     // ← just set deletedAt, don't remove from DB
    //     user.setDeletedAt(LocalDateTime.now());
    //     userRepo.save(user);
    //     return ResponseEntity.ok(new ApiResponseCustomize<>(200, "User soft deleted", null));
    // }
    // // force delete — permanently removes from DB
    // @DeleteMapping("/user/{userId}/force")
    // @Operation(summary = "Force delete user permanently")
    // public ResponseEntity<?> forceDelete(@PathVariable Long userId) {
    //     userRepo.findByIdIncludeDeleted(userId)
    //             .orElseThrow(() -> new NoSuchElementException("Force delete failed: Invalid user"));
    //     userRepo.forceDeleteById(userId);  // ← permanently removes from DB
    //     return ResponseEntity.ok(new ApiResponseCustomize<>(200, "User permanently deleted", null));
    // }
}
