package com.hkimhab.basic.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.hkimhab.basic.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

    // original long method (Spring Data generates the query)
    // ← search by firstName or lastName (case insensitive)
    List<User> findByFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCase(
            String firstName, String lastName
    );

    // ← short alias that calls the long one
    default List<User> searchByName(String name) {
        return findByFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCase(name, name);
    }

    @Query("SELECT u FROM User u WHERE u.id = :id")
    Optional<User> findByIdIncludeDeleted(@Param("id") Long id);

    // ← bypasses @SQLRestriction for force delete
    @Modifying
    @Transactional
    @Query("DELETE FROM User u WHERE u.id = :id")
    void forceDeleteById(@Param("id") Long id);
}
