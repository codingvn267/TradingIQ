package com.example.tradingiq.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

/**
 * Represents a user in the system.
 * This class is mapped to the "users" table in the database.
 * It contains fields for user details such as name, email, phone, date of birth, password, and balance.
 */

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String name;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String phone;

    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private Date dob;

    @Column(nullable = false)
    private String password;

    private double balance;
}
