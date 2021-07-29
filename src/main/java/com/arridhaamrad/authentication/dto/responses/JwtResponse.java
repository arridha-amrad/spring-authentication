package com.arridhaamrad.authentication.dto.responses;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class JwtResponse {
   private String jwtToken;
   private Long id;
   private String username;
   private String email;
   private List<String> roles;
}
