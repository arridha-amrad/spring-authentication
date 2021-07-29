package com.arridhaamrad.authentication.validators;

import com.arridhaamrad.authentication.customAnnotations.UserEmailUniqueConstraint;
import com.arridhaamrad.authentication.models.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UserEmailUniqueConstraintValidator implements ConstraintValidator<UserEmailUniqueConstraint, String> {
   @Autowired
   private UserRepository userRepository;

   @Override
   public boolean isValid(String email, ConstraintValidatorContext context) {
      return !userRepository.existsByEmail(email);
   }
}
