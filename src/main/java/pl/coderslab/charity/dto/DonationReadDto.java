package pl.coderslab.charity.dto;

import lombok.Data;
import pl.coderslab.charity.entity.Category;
import pl.coderslab.charity.entity.Institution;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Data
public class DonationReadDto {
    private Long id;
    private Integer quantity;
    private List<Category> categories;
    private Institution institution;
    private String street;
    private String city;
    private String zipCode;
    private String phoneNumber;
    private LocalDate pickUpDate;
    private LocalTime pickUpTime;
    private String pickUpComment;
    private UserReadDto user;
    // Additional
    private String created;
    private String pickedUpDateTime;
    private int status;
}
