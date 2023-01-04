package pl.coderslab.charity.validation;

import lombok.RequiredArgsConstructor;
import pl.coderslab.charity.dto.UserEditDto;
import pl.coderslab.charity.entity.User;
import pl.coderslab.charity.service.UserService;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@RequiredArgsConstructor
public class UniqueEmailEditValidator implements ConstraintValidator<UniqueEmail, UserEditDto> {
    private final UserService userService;

    @Override
    public void initialize(UniqueEmail constraintAnnotation) {
    }

    @Override
    public boolean isValid(UserEditDto userEditDto, ConstraintValidatorContext constraintValidatorContext) {
        User checkUser = userService.findByUserEmail(userEditDto.getEmail());
        if (checkUser != null && !checkUser.getId().equals(userEditDto.getId())) {
            constraintValidatorContext
                    .buildConstraintViolationWithTemplate("{invalid.email.email-unique}")
                    .addPropertyNode("email")
                    .addConstraintViolation();
            return false;
        }
        return true;
    }
}
