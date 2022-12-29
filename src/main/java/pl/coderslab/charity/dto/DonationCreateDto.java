package pl.coderslab.charity.dto;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import pl.coderslab.charity.entity.Category;
import pl.coderslab.charity.entity.Institution;

import javax.validation.constraints.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Data
public class DonationCreateDto {

    @Min(1)
    private Integer quantity;

    @NotNull(message = "{invalid.category.category-notnull}")
    private List<Category> categories;

    @NotNull(message = "{invalid.institution.institution-notnull}")
    private Institution institution;

    @NotEmpty
    @Size(min = 3, max = 50)
    private String street;

    @NotEmpty
    @Size(min = 3, max = 50)
    private String city;

    @NotEmpty
    @Pattern(regexp = "\\d{2}-\\d{3}", message = "{invalid.zipcode.zipcode-pattern}")
    private String zipCode;

    @NotEmpty
    @Pattern(regexp = "(?<!\\w)(\\(?(\\+|00)?48\\)?)?[ -]?\\d{3}[ -]?\\d{3}[ -]?\\d{3}(?!\\w)", message = "{invalid.phoneNumber.phoneNumber-pattern}")
    private String phoneNumber;

    @NotEmpty
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private LocalDate pickUpDate;

    @NotEmpty
    @DateTimeFormat(pattern = "hh:mm")
    private LocalTime pickUpTime;

    @Size(max = 600, message = "{invalid.pickUpComment.pickUpComment-length}")
    private String pickUpComment;

}
