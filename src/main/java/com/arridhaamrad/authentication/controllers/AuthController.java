package com.arridhaamrad.authentication.controllers;

import com.arridhaamrad.authentication.dto.responses.ResponseData;
import com.arridhaamrad.authentication.dto.requests.SignupRequestData;
import com.arridhaamrad.authentication.models.entities.RoleEntity;
import com.arridhaamrad.authentication.models.entities.UserEntity;
import com.arridhaamrad.authentication.models.enums.RoleEnum;
import com.arridhaamrad.authentication.services.RoleService;
import com.arridhaamrad.authentication.services.UserServices;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

   @Autowired
   private UserServices userServices;

   @Autowired
   private RoleService roleService;

   @Autowired
   private ModelMapper modelMapper;


   @GetMapping
   public String welcome(){
      return "Hello World";
   }

   @PostMapping
   public ResponseEntity<ResponseData<UserEntity>> register(@Valid @RequestBody SignupRequestData signupRequestData){
      System.out.println("enum : " + RoleEnum.ROLE_USER); // ROLE_USER
      Set<RoleEntity> roles = new HashSet<>();
      ResponseData<UserEntity> responseDta = new ResponseData<>();
      if(signupRequestData.getRoles().size() == 0){
         RoleEntity role = roleService.findByName(RoleEnum.ROLE_USER).get();
         roles.add(role);
      }
      signupRequestData.getRoles().forEach(role -> {
         switch(role){
            case "admin":
               RoleEntity adminRole = roleService.findByName(RoleEnum.ROLE_ADMIN).get();
               roles.add(adminRole);
               break;
            case "mod":
               RoleEntity modRole = roleService.findByName(RoleEnum.ROLE_MODERATOR).get();
               roles.add(modRole);
               break;
            default:
               RoleEntity userRole = roleService.findByName(RoleEnum.ROLE_USER).get();
               roles.add(userRole);
               break;
         }
      });
      UserEntity user = modelMapper.map(signupRequestData, UserEntity.class);
      user.setRoles(roles);
      responseDta.setStatus(HttpStatus.CREATED.value());
      responseDta.setPayload(userServices.registerUser(user));
      return ResponseEntity.ok(responseDta);
   }
}
