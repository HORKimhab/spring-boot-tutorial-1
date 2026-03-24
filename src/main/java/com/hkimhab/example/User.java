package com.hkimhab.example;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "users")  
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;
}

// Hibernate will generate this table:
// sqlCREATE TABLE users (
//     id       BIGSERIAL PRIMARY KEY,
//     first_name VARCHAR(255) NOT NULL,
//     last_name  VARCHAR(255) NOT NULL,
//     email      VARCHAR(255) NOT NULL UNIQUE,
//     password   VARCHAR(255) NOT NULL
// );
