package com.arridhaamrad.authentication.dto.requests;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class SignupRequestData {
   private String username;
   private String email;
   private String password;
   private List<String> roles = new ArrayList<>();
}
