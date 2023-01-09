package pl.coderslab.charity.dto;

import lombok.Data;
import pl.coderslab.charity.entity.Role;

import java.util.Set;

@Data
public class UserDetailsDto {
    private Long id;
    private String username;
    private String name;
    private String surname;
    private String email;
    private int enabled;
    private Set<Role> roles;
}
