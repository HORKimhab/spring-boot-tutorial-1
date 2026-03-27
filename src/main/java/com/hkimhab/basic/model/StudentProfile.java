package com.hkimhab.basic.model;

import java.time.LocalDateTime;

import org.hibernate.annotations.SQLRestriction;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "student_profiles")
@NoArgsConstructor   // ← Jackson needs this to deserialize JSON
@AllArgsConstructor
@SQLRestriction("deleted_at IS NULL")  // ← automatically filters deleted student_profiles in all queries
public class StudentProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "student_profiles_seq")
    @SequenceGenerator(
            name = "student_profiles_seq",
            sequenceName = "student_profiles_id_seq",
            allocationSize = 1
    )
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;

    private String bio;

    public StudentProfile(String bio) {
        this.bio = bio;
    }

    public Integer getId() {
        return id != null ? id.intValue() : null;
    }

    // ← null = active, not null = soft deleted
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private LocalDateTime deletedAt;

    @OneToOne(
            fetch = FetchType.LAZY // ← don't load User unless needed
    )
    @JoinColumn(
            name = "user_id", // ← FK column name in student_profiles table
            nullable = false,
            unique = true // ← ensures true OneToOne (one profile per user)
    )
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @Schema(
            description = "User linked to this profile",
            accessMode = Schema.AccessMode.READ_ONLY
    )
    private User user;
}
