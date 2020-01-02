package ua.com.parkhub.mappers;

import ua.com.parkhub.dto.RoleDTO;
import ua.com.parkhub.dto.UserDTO;
import ua.com.parkhub.exceptions.ParkHubException;
import ua.com.parkhub.persistence.entities.User;
import ua.com.parkhub.persistence.entities.UserRole;

import java.util.Arrays;

public class UserMapper {

    private UserMapper(){}

    public static User persist(UserDTO userDTO) {
        if(userDTO == null) {
            throw new ParkHubException("UserDTO to be mapped to User entity is null.");
        }
        User userEntity = new User();
        userEntity.setEmail(userDTO.getEmail());
        userEntity.setFirstName(userDTO.getFirstName());
        userEntity.setLastName(userDTO.getLastName());
        userEntity.setNumberOfFaildPassEntering(userDTO.getNumber_of_faild_pass_entering());
        UserRole role = new UserRole();
        if (userDTO.getRole() != null) {
            role.setRoleName(userDTO.getRole().getRoleName());
        }
        userEntity.setRole(role);
        return userEntity;
    }

    public static UserDTO detach(User user) {
        if(user == null) {
            throw new ParkHubException("User entity to be converted to UserDTO is null.");
        }
        UserDTO userDto = new UserDTO();
        userDto.setEmail(user.getEmail());
        userDto.setFirstName(user.getFirstName());
        userDto.setLastName(user.getLastName());
        userDto.setId(user.getId());
        userDto.setNumber_of_faild_pass_entering(user.getNumberOfFaildPassEntering());
        UserRole role = user.getRole();
        if(role != null && role.getRoleName() != null) {
            String roleName = role.getRoleName().toUpperCase();
            switch (roleName) {
                case "ADMIN":
                    userDto.setRole(RoleDTO.ADMIN);
                    break;
                case "USER":
                    userDto.setRole(RoleDTO.USER);
                    break;
                case "MANAGER":
                    userDto.setRole(RoleDTO.MANAGER);
                    break;
                case "PENDING":
                    userDto.setRole(RoleDTO.PENDING);
                    break;
                default:
                    throw new ParkHubException(String.format("Not known role name: %s. Role name may be one of the following: %s).", roleName, Arrays.asList(RoleDTO.values())));
            }
        }
        return userDto;
    }
}
