package pl.coderslab.charity.validation;

import pl.coderslab.charity.dto.UserResetPassDto;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ConfirmResetPasswordValidator implements ConstraintValidator<ConfirmPassword, UserResetPassDto> {

    @Override
    public void initialize(ConfirmPassword constraintAnnotation) {
    }

    @Override
    public boolean isValid(UserResetPassDto userResetPassDto, ConstraintValidatorContext constraintValidatorContext) {
        if (!userResetPassDto.getNewPassword().equals(userResetPassDto.getMatchingNewPassword())) {
            constraintValidatorContext
                    .buildConstraintViolationWithTemplate("{invalid.password.confirm-password}")
                    .addPropertyNode("matchingNewPassword")
                    .addConstraintViolation();
            return false;
        }
        return true;
    }
}
