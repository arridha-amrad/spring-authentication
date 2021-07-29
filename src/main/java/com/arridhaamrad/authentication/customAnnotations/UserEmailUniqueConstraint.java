package com.arridhaamrad.authentication.customAnnotations;

import com.arridhaamrad.authentication.validators.UserEmailUniqueConstraintValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = UserEmailUniqueConstraintValidator.class)
public @interface UserEmailUniqueConstraint {

   String message() default "";
   Class<?>[] groups() default {};
   Class<? extends Payload>[] payload() default {};
}
