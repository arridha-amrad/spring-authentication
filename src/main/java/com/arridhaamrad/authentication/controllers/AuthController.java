package com.arridhaamrad.authentication.controllers;

import com.arridhaamrad.authentication.dto.exceptions.CustomResponse;
import com.arridhaamrad.authentication.dto.requests.LoginRequestData;
import com.arridhaamrad.authentication.dto.responses.BadRequestResponse;
import com.arridhaamrad.authentication.dto.responses.JwtResponse;
import com.arridhaamrad.authentication.dto.responses.ResponseData;
import com.arridhaamrad.authentication.dto.requests.SignupRequestData;
import com.arridhaamrad.authentication.exceptions.LoginFailedException;
import com.arridhaamrad.authentication.models.entities.RoleEntity;
import com.arridhaamrad.authentication.models.entities.UserEntity;
import com.arridhaamrad.authentication.models.enums.RoleEnum;
import com.arridhaamrad.authentication.services.RoleService;
import com.arridhaamrad.authentication.services.UserServices;
import com.arridhaamrad.authentication.utils.JwtUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

   @Autowired
   private UserServices userServices;

   @Autowired
   private RoleService roleService;

   @Autowired
   private ModelMapper modelMapper;

   @Autowired
   private AuthenticationManager authenticationManager;

   @Autowired
   private JwtUtils jwtUtils;

   @GetMapping
   public String welcome(){
      return "Hello World";
   }

   @PostMapping("/register")
   public ResponseEntity<?> register(
        @Valid @RequestBody SignupRequestData signupRequestData,
        Errors errors
   ){
      if (errors.hasErrors()){
         return CustomResponse.badRequestResponse(errors);
      }

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

   @PostMapping("/login")
   public ResponseEntity<?> login(
       @Valid
       @RequestBody LoginRequestData loginData,
       Errors errors
   ){
      if (errors.hasErrors()){
         BadRequestResponse badRequestResponse = new BadRequestResponse();
         for (ObjectError error: errors.getAllErrors()){
            badRequestResponse.getMessages().add(error.getDefaultMessage());
         }
         return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(badRequestResponse);
      }
      Authentication authentication;
      try {
         authentication = authenticationManager.authenticate(
              new UsernamePasswordAuthenticationToken(
                   loginData.getUsername(),
                   loginData.getPassword()
              )
         );
      } catch (BadCredentialsException e){
         throw new LoginFailedException("invalid username/email and password");
      }
      SecurityContextHolder.getContext().setAuthentication(authentication);
      UserEntity userEntity = (UserEntity) authentication.getPrincipal();
      // generate token
      String token = jwtUtils.generateTokenWithUsername(userEntity.getUsername());
      // get user roles
      List<String> roles = userEntity.getAuthorities().stream().map(role -> role.getAuthority()).collect(Collectors.toList());
      // return token with user entity
      return ResponseEntity.ok(
         new JwtResponse(
             token,
             userEntity.getId(),
             userEntity.getUsername(),
             userEntity.getEmail(),
             roles
         )
      );
   }

}
