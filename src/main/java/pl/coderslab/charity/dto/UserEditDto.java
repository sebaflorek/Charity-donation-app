package pl.coderslab.charity.dto;

import lombok.Data;
import pl.coderslab.charity.validation.UniqueEmail;
import pl.coderslab.charity.validation.UniqueUsername;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
@UniqueEmail
@UniqueUsername
public class UserEditDto {
    private Long id;

    @NotBlank
    @Size(min = 3, max = 20, message = "{invalid.username.username-length}")
    private String username;
    private String name;
    private String surname;

    @NotEmpty
    @Email
    private String email;

}
