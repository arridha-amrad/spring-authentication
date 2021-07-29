package com.arridhaamrad.authentication.dto.requests;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class LoginRequestData {
   @NotEmpty(message = "username is required")
   private String username;

   @NotEmpty(message = "password is required")
   private String password;
}
