package com.arridhaamrad.authentication.services;

import com.arridhaamrad.authentication.exceptions.ResourceNotFoundException;
import com.arridhaamrad.authentication.models.entities.UserEntity;
import com.arridhaamrad.authentication.models.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserServices implements UserDetailsService {

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

   @Override
   public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
      UserDetails userDetails;
      if (username.contains("@")){
         userDetails = userRepository.findByEmail(username).orElseThrow(() -> new ResourceNotFoundException("User not found"));
      } else {
         userDetails = userRepository.findByUsername(username).orElseThrow(() -> new ResourceNotFoundException("User not found"));
      }
      return userDetails;
   }
}
