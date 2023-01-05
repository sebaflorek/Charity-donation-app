package pl.coderslab.charity.dto;

import lombok.Data;
import pl.coderslab.charity.validation.CheckPasswordComplexity;
import pl.coderslab.charity.validation.ConfirmPassword;

import javax.validation.constraints.NotEmpty;

@Data
@ConfirmPassword
@CheckPasswordComplexity
public class UserResetPassDto {
    //@NotEmpty
    private String newPassword;
    @NotEmpty
    private String matchingNewPassword;
}
