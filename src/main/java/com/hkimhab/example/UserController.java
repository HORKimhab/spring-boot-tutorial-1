package com.hkimhab.example;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    public UserController(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    @PostMapping("/user")
    @Operation(summary = "Create new user", description = "Add a new user to the database")
    public User post(
            @Valid
            @org.springframework.web.bind.annotation.RequestBody
            @RequestBody(
                    description = "User object to be created",
                    required = true,
                    content = @Content(schema = @Schema(implementation = User.class))) User user
    ) {
        System.out.println(user); // 🔍 check values;
        return userRepo.save(user);
    }
}
