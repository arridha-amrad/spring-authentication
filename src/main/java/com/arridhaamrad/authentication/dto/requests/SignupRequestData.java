package com.arridhaamrad.authentication.dto.requests;

import com.arridhaamrad.authentication.customAnnotations.UserEmailUniqueConstraint;
import com.arridhaamrad.authentication.customAnnotations.UserUsernameUniqueConstraint;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class SignupRequestData {
   @Pattern(
        regexp = "^[a-zA-Z0-9]([._-](?![._-])|[a-zA-Z0-9]){3,18}[a-zA-Z0-9]$",
        message = "invalid username")
   @UserUsernameUniqueConstraint(message = "username has been used")
   private String username;

   @NotEmpty(message = "email is required")
   @Email(message = "invalid email")
   @UserEmailUniqueConstraint(message = "email has been registered")
   private String email;

   @Pattern(
        regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{8,}$",
        message = "password requires 8 characters with uppercase, lowercase and number"
   )
   private String password;

   private List<String> roles = new ArrayList<>();
}
