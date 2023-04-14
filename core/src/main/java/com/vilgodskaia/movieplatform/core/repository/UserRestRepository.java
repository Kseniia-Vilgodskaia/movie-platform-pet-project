package com.vilgodskaia.movieplatform.core.repository;

import com.vilgodskaia.movieplatform.core.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserRestRepository extends JpaRepository<User, UUID> {
    Optional<User> findByLoginIgnoreCase(String login);
}
