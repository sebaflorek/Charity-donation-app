package pl.coderslab.charity.validation;

import pl.coderslab.charity.dto.UserCreateDto;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ConfirmPasswordValidator implements ConstraintValidator<ConfirmPassword, UserCreateDto> {

    @Override
    public void initialize(ConfirmPassword constraintAnnotation) {
    }

    @Override
    public boolean isValid(UserCreateDto userCreateDto, ConstraintValidatorContext constraintValidatorContext) {
        if (!userCreateDto.getPassword().equals(userCreateDto.getMatchingPassword())) {
            constraintValidatorContext
                    .buildConstraintViolationWithTemplate("{invalid.password.confirm-password}")
                    .addPropertyNode("matchingPassword")
                    .addConstraintViolation();
            return false;
        }
        return true;
    }
}
