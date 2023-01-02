package pl.coderslab.charity.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = Institution.TABLE_NAME)
@Data
public class Institution {
    public static final String TABLE_NAME = "institutions";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;
}
