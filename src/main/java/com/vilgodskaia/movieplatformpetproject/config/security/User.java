package com.vilgodskaia.movieplatformpetproject.config.security;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Collection;
import java.util.UUID;

@Entity
@Table(name = "user")
@Getter
@Setter
@NoArgsConstructor
public class User {

    /**
     * ID
     */
    @Id
    private UUID id;

    /**
     * Login
     */
    @Column(name = "login", nullable = false, unique = true)
    private String login;

    /**
     * Password
     */
    @Column(name = "password", nullable = false)
    private String password;

    /**
     * First name
     */
    @Column(name = "first_name", nullable = false)
    private String firstName;

    /**
     * Last name
     */
    @Column(name = "last_name", nullable = false)
    private String lastName;

    /**
     * Email
     */
    @Column(name = "email", nullable = false, unique = true)
    private String email;

    /**
     * Roles
     */
    @ElementCollection(targetClass = Role.class)
    @CollectionTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "role", nullable = false)
    @Enumerated(EnumType.STRING)
    private Collection<Role> roles;
}
