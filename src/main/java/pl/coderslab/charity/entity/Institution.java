package pl.coderslab.charity.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = Institution.TABLE_NAME)
@Data
public class Institution {
    public static final String TABLE_NAME = "institutions";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String name;

    @NotBlank
    private String description;
}
