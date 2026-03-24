package com.hkimhab.example;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "users")
@NoArgsConstructor   // ← Jackson needs this to deserialize JSON
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "users_seq")
    @SequenceGenerator(
            name = "users_seq",
            sequenceName = "users_id_seq",
            allocationSize = 1
    )
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;

    @Column(nullable = false)
    @Schema(example = "H", description = "First name")
    private String firstName;

    @Column(nullable = false)
    @Schema(example = "Kimhab", description = "Last name")
    private String lastName;

    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    @Column(nullable = false, unique = true)
    @Schema(example = "hkimhab@gmail.com", description = "Unique email address")

    private String email;

    @Column(nullable = false)
    @Schema(example = "22", description = "User age")
    private int age;

    @Column(nullable = false)
    @Schema(example = "password12345", description = "User password")
    private String password;
}
