package pl.coderslab.charity.entity;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.Clock;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Entity
@Table(name = Donation.TABLE_NAME)
@Data
public class Donation {
    public static final String TABLE_NAME = "donations";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer quantity;

    @ManyToMany
    @JoinTable(name = "donations_categories",
            joinColumns = @JoinColumn(name = "donation_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id"))
    private List<Category> categories;

    @ManyToOne
    @JoinColumn(name = "institution_id")
    private Institution institution;

    private String street;

    private String city;

    private String zipCode;

    private String phoneNumber;

    private LocalDate pickUpDate;

    private LocalTime pickUpTime;

    private String pickUpComment;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    // Additional
    private LocalDateTime created;

    private LocalDateTime pickedUpDateTime;

    @Column(columnDefinition = "tinyint default 0")
    private int status;

    @PrePersist
    public void prePersist() {
        created = LocalDateTime.now();
    }
}
