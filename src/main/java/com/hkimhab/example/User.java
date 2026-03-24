package com.hkimhab.example;

import java.time.LocalDateTime;

import org.hibernate.annotations.SQLRestriction;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.hkimhab.example.model.School;
import com.hkimhab.example.model.StudentProfile;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
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
@SQLRestriction("deleted_at IS NULL")  // ← automatically filters deleted users in all queries
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

    // This side says:
    // "I have one StudentProfile"
    // "StudentProfile owns the relationship (has the FK column)"
    // "If I get deleted/saved, do the same to StudentProfile"
    @OneToOne(
            mappedBy = "user", // ← refers to the 'user' field in StudentProfile
            cascade = CascadeType.ALL, // ← save/delete User = save/delete StudentProfile
            fetch = FetchType.LAZY, // ← don't load StudentProfile unless needed
            orphanRemoval = true // ← if StudentProfile is removed from User, delete it from DB
    )
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)  // ← hide from request body
    @Schema(
            description = "Student profile linked to this user",
            accessMode = Schema.AccessMode.READ_ONLY
    )
    private StudentProfile studentProfile;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private LocalDateTime deletedAt;  // ← null = active, not null = soft deleted

    // ← owner side — has the FK column school_id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "school_id", nullable = false)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @Schema(
            description = "School this user belongs to",
            accessMode = Schema.AccessMode.READ_ONLY
    )
    private School school;
}
