package com.arridhaamrad.authentication.services;

import com.arridhaamrad.authentication.models.entities.RoleEntity;
import com.arridhaamrad.authentication.models.enums.RoleEnum;
import com.arridhaamrad.authentication.models.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RoleService {
   @Autowired
   RoleRepository roleRepository;

   public Optional<RoleEntity> findByName(RoleEnum roleName){
      return roleRepository.findByName(roleName);
   }
}
