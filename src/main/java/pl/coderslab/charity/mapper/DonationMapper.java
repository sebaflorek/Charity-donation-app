package pl.coderslab.charity.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pl.coderslab.charity.dto.DonationCreateDto;
import pl.coderslab.charity.dto.DonationReadDto;
import pl.coderslab.charity.entity.Donation;

@Mapper(componentModel = "spring")
public interface DonationMapper {
    Donation donationCreateDtoToDonation(DonationCreateDto donationCreateDto);

    @Mapping(source = "created", target = "created", dateFormat = "yyyy-MM-dd HH:mm")
    @Mapping(source = "pickedUpDateTime", target = "pickedUpDateTime", dateFormat = "yyyy-MM-dd HH:mm")
    DonationReadDto donationToDonationReadDto(Donation donation);

}
