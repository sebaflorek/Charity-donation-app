package pl.coderslab.charity.validation;

import pl.coderslab.charity.dto.UserChangePassDto;
import pl.coderslab.charity.dto.UserCreateDto;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ConfirmNewPasswordValidator implements ConstraintValidator<ConfirmPassword, UserChangePassDto> {

    @Override
    public void initialize(ConfirmPassword constraintAnnotation) {
    }

    @Override
    public boolean isValid(UserChangePassDto userChangePassDto, ConstraintValidatorContext constraintValidatorContext) {
        if (!userChangePassDto.getNewPassword().equals(userChangePassDto.getMatchingNewPassword())) {
            constraintValidatorContext
                    .buildConstraintViolationWithTemplate("{invalid.password.confirm-password}")
                    .addPropertyNode("matchingNewPassword")
                    .addConstraintViolation();
            return false;
        }
        return true;
    }
}
