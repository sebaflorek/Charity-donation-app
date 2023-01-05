package pl.coderslab.charity.validation;

import pl.coderslab.charity.dto.UserResetPassDto;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

public class CheckResetPasswordComplexityValidator implements ConstraintValidator<CheckPasswordComplexity, UserResetPassDto> {

    private final Pattern PASSWORD_PATTERN = Pattern.compile("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$");

    @Override
    public void initialize(CheckPasswordComplexity constraintAnnotation) {
    }

    @Override
    public boolean isValid(UserResetPassDto userResetPassDto, ConstraintValidatorContext constraintValidatorContext) {
        if (!PASSWORD_PATTERN.matcher(userResetPassDto.getNewPassword()).matches()) {
            constraintValidatorContext
                    .buildConstraintViolationWithTemplate("{invalid.password.password-complexity}")
                    .addPropertyNode("newPassword")
                    .addConstraintViolation();
            return false;
        }
        return true;
    }
}
