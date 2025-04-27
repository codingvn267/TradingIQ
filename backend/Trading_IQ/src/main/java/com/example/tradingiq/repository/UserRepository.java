package com.example.tradingiq.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.tradingiq.model.User;

import java.util.Optional;

/**
 * Repository interface for managing user data in the database.
 * Extends JpaRepository to provide CRUD operations and custom queries.
 */

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByEmail(String email);
}

