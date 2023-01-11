package pl.coderslab.charity.dto;

import lombok.Data;

@Data
public class UserReadDto {
    private Long id;
    private String username;
    private String name;
    private String surname;
    private String email;
}
