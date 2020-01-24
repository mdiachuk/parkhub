package ua.com.parkhub.mappers.modelToDto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.com.parkhub.dto.RoleDTO;
import ua.com.parkhub.dto.UserResponseDTO;
import ua.com.parkhub.mappers.Mapper;
import ua.com.parkhub.model.enums.RoleModel;
import ua.com.parkhub.model.UserModel;

@Component
public class UserModelToUserResponseDtoMapper implements Mapper<UserModel, UserResponseDTO> {

    private Mapper<RoleModel, RoleDTO> roleModelToDTOMapper;

    @Autowired
    public UserModelToUserResponseDtoMapper(Mapper<RoleModel, RoleDTO> roleModelToDTOMapper) {
        this.roleModelToDTOMapper = roleModelToDTOMapper;
    }

    @Override
    public UserResponseDTO transform(UserModel model) {
        UserResponseDTO userResponseDTO = new UserResponseDTO();
        userResponseDTO.setId(model.getId());
        userResponseDTO.setFirstName(model.getFirstName());
        userResponseDTO.setLastName(model.getLastName());
        userResponseDTO.setRole(model.getRole().getRoleName());
        userResponseDTO.setEmail(model.getEmail());
        userResponseDTO.setToken(model.getToken());
        userResponseDTO.setNumberOfFailedPassEntering(model.getNumberOfFailedPassEntering());
        return  userResponseDTO;
    }
}
