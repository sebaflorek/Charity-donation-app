package pl.coderslab.charity.validation;

import pl.coderslab.charity.dto.UserCreateDto;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

public class CheckPasswordComplexityValidator implements ConstraintValidator<CheckPasswordComplexity, UserCreateDto> {

    private final Pattern PASSWORD_PATTERN = Pattern.compile("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$");

    @Override
    public void initialize(CheckPasswordComplexity constraintAnnotation) {
    }

    @Override
    public boolean isValid(UserCreateDto userCreateDto, ConstraintValidatorContext constraintValidatorContext) {
        if (!PASSWORD_PATTERN.matcher(userCreateDto.getPassword()).matches()) {
            constraintValidatorContext
                    .buildConstraintViolationWithTemplate("{invalid.password.password-complexity}")
                    .addPropertyNode("password")
                    .addConstraintViolation();
            return false;
        }
        return true;
    }
}
