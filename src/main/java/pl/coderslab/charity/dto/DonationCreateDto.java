package pl.coderslab.charity.dto;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import pl.coderslab.charity.entity.Category;
import pl.coderslab.charity.entity.Institution;

import javax.validation.constraints.*;
import java.time.LocalDate;
import java.util.List;

@Data
public class DonationCreateDto {

    @Min(1)
    private String quantity;

    @NotEmpty(message = "{invalid.category.category-notnull}")
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

    @NotNull
    @Future
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate pickUpDate;

    @NotEmpty
    @Pattern(regexp = "^([0-1]?[0-9]|2[0-3]):[0-5][0-9]$", message = "{invalid.pickUpTime.pickUpTime-pattern}")
    private String pickUpTime;

    @Size(max = 600, message = "{invalid.pickUpComment.pickUpComment-length}")
    private String pickUpComment;

}
