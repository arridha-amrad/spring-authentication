package com.arridhaamrad.authentication.controllers;

import com.arridhaamrad.authentication.models.entities.UserEntity;
import com.arridhaamrad.authentication.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

   @Autowired
   public UserServices userServices;

   @GetMapping
   public String welcome(){
      return "Hello World";
   }

   @PostMapping
   public UserEntity register(@Valid @RequestBody UserEntity userEntity){
      return userServices.registerUser(userEntity);
   }
}
