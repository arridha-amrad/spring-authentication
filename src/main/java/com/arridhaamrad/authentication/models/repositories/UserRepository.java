package com.arridhaamrad.authentication.models.repositories;

import com.arridhaamrad.authentication.models.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
   Optional<UserEntity> findByUsername(String username);
   Optional<UserEntity> findByEmail(String email);
}
