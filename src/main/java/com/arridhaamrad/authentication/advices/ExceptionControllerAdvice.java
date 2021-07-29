package com.arridhaamrad.authentication.advices;

import com.arridhaamrad.authentication.dto.exceptions.GeneralExceptionData;
import com.arridhaamrad.authentication.exceptions.LoginFailedException;
import com.arridhaamrad.authentication.exceptions.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionControllerAdvice {

   @ExceptionHandler(ResourceNotFoundException.class)
   public ResponseEntity<?> notFoundException(ResourceNotFoundException ex){
      GeneralExceptionData exData = new GeneralExceptionData();
      exData.setMessage(ex.getMessage());
      exData.setStatus(HttpStatus.NOT_FOUND.value());
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exData);
   }

   @ExceptionHandler(LoginFailedException.class)
   public ResponseEntity<?> loginFailed(LoginFailedException exception) {
      GeneralExceptionData generalExceptionData = new GeneralExceptionData();
      generalExceptionData.setMessage(exception.getMessage());
      generalExceptionData.setStatus(HttpStatus.FORBIDDEN.value());
      return ResponseEntity.status(HttpStatus.FORBIDDEN).body(generalExceptionData);
   }
}
