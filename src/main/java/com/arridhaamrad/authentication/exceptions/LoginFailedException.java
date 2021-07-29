package com.arridhaamrad.authentication.exceptions;

import java.util.Date;

public class LoginFailedException extends RuntimeException{
   private final Date date = new Date();
   public LoginFailedException(String message){
      super(message);
   }
}
