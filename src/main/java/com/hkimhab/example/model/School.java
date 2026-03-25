package com.hkimhab.example.model;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.SQLRestriction;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "schools")
@NoArgsConstructor   // ← Jackson needs this to deserialize JSON
@AllArgsConstructor
@SQLRestriction("deleted_at IS NULL")  // ← automatically filters deleted schools in all queries
public class School {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "schools_seq")
    @SequenceGenerator(
            name = "schools_seq",
            sequenceName = "schools_id_seq",
            allocationSize = 1
    )
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;

    private String name;

    public School(String name) {
        this.name = name;
    }

    public Long getId() {
        return id != null ? id.longValue() : null;
    }

    @OneToMany(
            mappedBy = "school", // ← refers to 'school' field in User entity
            cascade = CascadeType.ALL, // ← save/delete School = save/delete all Users
            fetch = FetchType.LAZY, // ← don't load Users unless needed
            orphanRemoval = true // ← if User removed from list, delete from DB
    )
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @Schema(
            description = "List of users belonging to this school",
            accessMode = Schema.AccessMode.READ_ONLY
    )
    private List<User> users = new ArrayList<>();  // ← initialize to avoid NullPointerException
}
