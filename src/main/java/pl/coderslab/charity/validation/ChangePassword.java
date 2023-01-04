package pl.coderslab.charity.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ChangePasswordValidator.class)
public @interface ChangePassword {
    String message() default "{invalid.password.old-password}";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
