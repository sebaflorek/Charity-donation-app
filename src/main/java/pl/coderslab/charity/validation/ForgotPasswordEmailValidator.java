package pl.coderslab.charity.validation;

import lombok.RequiredArgsConstructor;
import pl.coderslab.charity.entity.User;
import pl.coderslab.charity.dto.UserForgotPassDto;
import pl.coderslab.charity.service.UserService;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@RequiredArgsConstructor
public class ForgotPasswordEmailValidator implements ConstraintValidator<ForgotPassword, UserForgotPassDto> {
    private final UserService userService;

    @Override
    public void initialize(ForgotPassword constraintAnnotation) {
    }

    @Override
    public boolean isValid(UserForgotPassDto userForgotPassDto, ConstraintValidatorContext constraintValidatorContext) {
        User user = userService.findByUserEmail(userForgotPassDto.getEmail());
        if (user == null) {
            constraintValidatorContext
                    .buildConstraintViolationWithTemplate("{invalid.email.email-notfound}")
                    .addPropertyNode("email")
                    .addConstraintViolation();
            return false;
        }
        if (user.getEnabled() == 0) {
            constraintValidatorContext
                    .buildConstraintViolationWithTemplate("{invalid.account.account-notActive}")
                    .addPropertyNode("email")
                    .addConstraintViolation();
            return false;
        }
        return true;
    }
}
