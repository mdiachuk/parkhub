package ua.com.parkhub.mapper;

import org.mapstruct.Mapper;
import ua.com.parkhub.dto.AddressDTO;
import ua.com.parkhub.dto.UserDTO;
import ua.com.parkhub.model.AddressModel;
import ua.com.parkhub.model.UserModel;
import ua.com.parkhub.persistence.entities.Address;
import ua.com.parkhub.persistence.entities.User;

@Mapper(componentModel = "spring")
public interface UserMapper {
    //AddressMapper  INSTANCE = Mappers.getMapper( AddressMapper.class );
    UserModel userToUserModel(User user);
    User userModelToUser(UserModel userModel);
    UserDTO userToUserDTO(User user);
    User userDTOToUser(UserDTO userDTO);
}
