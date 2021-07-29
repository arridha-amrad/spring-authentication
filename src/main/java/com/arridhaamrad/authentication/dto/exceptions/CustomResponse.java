package com.arridhaamrad.authentication.dto.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;

import java.util.HashMap;
import java.util.Map;

public class CustomResponse {

   public static ResponseEntity<?> badRequestResponse(Errors errors){
      Map<String, Object> data = new HashMap<>();
      for (ObjectError error: errors.getAllErrors()){
         if (error.getDefaultMessage().contains("username")){
            data.put(
                 "username",
                 error.getDefaultMessage()
            );
         }
         if (error.getDefaultMessage().contains("password")){
            data.put(
                 "password",
                 error.getDefaultMessage()
            );
         }
         if (error.getDefaultMessage().contains("email")){
            data.put(
                 "email",
                 error.getDefaultMessage()
            );
         }
      }
      FieldsError errorFields = new FieldsError();
      errorFields.getErrors().add(data);
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorFields);
   }
}
