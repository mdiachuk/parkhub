package ua.com.parkhub.mappers.dtoToModel;

import org.springframework.stereotype.Component;
import ua.com.parkhub.dto.UserDTO;
import ua.com.parkhub.mappers.Mapper;
import ua.com.parkhub.model.UserModel;

@Component
public class UserDtoToUserModelMapper implements Mapper<UserDTO, UserModel> {

    private final CustomerDtoToCustomerModelMapper customerDtoToCustomerModelMapper;
    private final RoleDtoToRoleModelMapper roleDtoToRoleModelMapper;

    public UserDtoToUserModelMapper(CustomerDtoToCustomerModelMapper customerDtoToCustomerModelMapper,
                                    RoleDtoToRoleModelMapper roleDtoToRoleModelMapper) {
        this.customerDtoToCustomerModelMapper = customerDtoToCustomerModelMapper;
        this.roleDtoToRoleModelMapper = roleDtoToRoleModelMapper;
    }

    @Override
    public UserModel transform(UserDTO from) {
        UserModel userModel = new UserModel();
        userModel.setPassword(from.getPassword());
        userModel.setEmail(from.getEmail());
        userModel.setCustomer(customerDtoToCustomerModelMapper.transform(from.getCustomer()));
        userModel.setFirstName(from.getFirstName());
        userModel.setLastName(from.getLastName());
        userModel.setId(from.getId());
        userModel.setRole(roleDtoToRoleModelMapper.transform(from.getRole()));
        userModel.setToken(from.getToken());
        userModel.setNumberOfFailedPassEntering(from.getNumberOfFailedPassEntering());
        return userModel;
    }
}
