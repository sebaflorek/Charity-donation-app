package pl.coderslab.charity.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {CheckPasswordComplexityValidator.class, CheckNewPasswordComplexityValidator.class, CheckResetPasswordComplexityValidator.class})
public @interface CheckPasswordComplexity {
    String message() default "{invalid.password.password-complexity}";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
