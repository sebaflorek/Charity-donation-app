package pl.coderslab.charity.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import pl.coderslab.charity.dto.UserDetailsDto;
import pl.coderslab.charity.dto.UserEditDto;
import pl.coderslab.charity.dto.UserReadDto;
import pl.coderslab.charity.entity.User;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserReadDto userToUserReadDto(User user);
    UserEditDto userToUserEditDto(User user);
    void userEditDtoToUserEntity(@MappingTarget User user, UserEditDto userEditDto);
    UserDetailsDto userToUserDetailsDto(User user);
}
