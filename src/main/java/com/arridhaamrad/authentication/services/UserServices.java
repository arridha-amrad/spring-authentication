package com.arridhaamrad.authentication.services;

import com.arridhaamrad.authentication.models.entities.UserEntity;
import com.arridhaamrad.authentication.models.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserServices {

   @Autowired
   BCryptPasswordEncoder bCryptPasswordEncoder;

   @Autowired
   UserRepository userRepository;

   public UserEntity registerUser(UserEntity user){
      user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
      return userRepository.save(user);
   }

   public List<UserEntity> findAll(){
      return userRepository.findAll();
   }

   public Optional<UserEntity> findByUsername(String username){
      return userRepository.findByUsername(username);
   }
}
