package pl.coderslab.charity.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = Category.TABLE_NAME)
@Data
public class Category {
    public static final String TABLE_NAME = "categories";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;
}
