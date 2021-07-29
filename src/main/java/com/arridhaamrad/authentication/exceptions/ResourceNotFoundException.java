package com.arridhaamrad.authentication.exceptions;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Date;

@ResponseStatus(HttpStatus.NOT_FOUND)
@Getter
@Setter
public class ResourceNotFoundException extends RuntimeException {
   private Date date = new Date();
   public ResourceNotFoundException(String message){
      super(message);
   }
}
