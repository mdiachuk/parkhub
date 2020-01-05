package ua.com.parkhub.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.com.parkhub.dto.UserDTO;
import ua.com.parkhub.model.UserModel;

@Component
public class UserModelToUserDtoMapper implements Mapper<UserModel, UserDTO> {

    private RoleModelToDTOMapper roleModelToDTOMapper;
//    private CustomerModelToDtoMapper customerModelToDtoMapper;
//    private SupportTicketModelToSupportTicketDtoMapper supportTicketModelToSupportTicketDtoMapper;

    @Autowired
    public UserModelToUserDtoMapper(RoleModelToDTOMapper roleModelToDTOMapper) {
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
        userDTO.setNumberOfFaildPassEntering(model.getNumberOfFaildPassEntering());
//        userDTO.setCustomerDTO(customerModelToDtoMapper.transform(model.getCustomer()));
//        userDTO.setTickets( ... );
        return userDTO;
    }
}
