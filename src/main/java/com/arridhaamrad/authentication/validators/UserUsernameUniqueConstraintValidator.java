package com.arridhaamrad.authentication.validators;

import com.arridhaamrad.authentication.customAnnotations.UserUsernameUniqueConstraint;
import com.arridhaamrad.authentication.models.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UserUsernameUniqueConstraintValidator implements ConstraintValidator<UserUsernameUniqueConstraint, String> {
   @Autowired
   private UserRepository userRepository;

   @Override
   public boolean isValid(String username, ConstraintValidatorContext constraintValidatorContext) {
      return !userRepository.existsByUsername(username);
   }
}
