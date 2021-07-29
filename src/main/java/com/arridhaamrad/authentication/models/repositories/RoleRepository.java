package com.arridhaamrad.authentication.models.repositories;

import com.arridhaamrad.authentication.models.entities.RoleEntity;
import com.arridhaamrad.authentication.models.enums.RoleEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<RoleEntity, Long> {
   Optional<RoleEntity> findByName(RoleEnum roleName);
}
