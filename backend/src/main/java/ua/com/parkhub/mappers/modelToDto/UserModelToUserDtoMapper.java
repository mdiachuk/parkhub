package ua.com.parkhub.mappers.modelToDto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.com.parkhub.dto.RoleDTO;
import ua.com.parkhub.dto.UserDTO;
import ua.com.parkhub.mappers.Mapper;
import ua.com.parkhub.model.enums.RoleModel;
import ua.com.parkhub.model.UserModel;

@Component
public class UserModelToUserDtoMapper implements Mapper<UserModel, UserDTO> {

    private Mapper<RoleModel, RoleDTO> roleModelToDTOMapper;

    @Autowired
    public UserModelToUserDtoMapper(Mapper<RoleModel, RoleDTO> roleModelToDTOMapper) {
        this.roleModelToDTOMapper = roleModelToDTOMapper;
    }

    @Override
    public UserDTO transform(UserModel model) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(model.getId());
        userDTO.setFirstName(model.getFirstName());
        userDTO.setLastName(model.getLastName());
        userDTO.setRole(roleModelToDTOMapper.transform(model.getRole()));
        userDTO.setEmail(model.getEmail());
        userDTO.setPassword(model.getPassword());
        userDTO.setToken(model.getToken());
        userDTO.setNumberOfFailedPassEntering(model.getNumberOfFailedPassEntering());
        return userDTO;
    }
}
