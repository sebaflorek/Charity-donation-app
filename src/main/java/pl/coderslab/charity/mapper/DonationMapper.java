package pl.coderslab.charity.mapper;

import org.mapstruct.Mapper;
import pl.coderslab.charity.dto.DonationCreateDto;
import pl.coderslab.charity.entity.Donation;

@Mapper(componentModel = "spring")
public interface DonationMapper {
    Donation donationCreateDtoToDonation(DonationCreateDto donationCreateDto);

}
