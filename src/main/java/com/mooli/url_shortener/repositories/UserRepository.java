package com.mooli.url_shortener.repositories;

import com.mooli.url_shortener.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByUsernameOrEmailAddress(String username, String emailAddress);
    Optional<User> findUsersByUsernameOrEmailAddress(String username, String emailAddress);
    Optional<User> findUsersByEmailAddress(String emailAddress);
    Optional<User> findUsersByUsername(String username);
}
