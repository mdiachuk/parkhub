package ua.com.parkhub.mappers.dtoToModel;

import org.springframework.stereotype.Component;
import ua.com.parkhub.dto.CustomerDTO;
import ua.com.parkhub.dto.RoleDTO;
import ua.com.parkhub.dto.UserDTO;
import ua.com.parkhub.mappers.Mapper;
import ua.com.parkhub.model.CustomerModel;
import ua.com.parkhub.model.UserModel;
import ua.com.parkhub.model.enums.RoleModel;

@Component
public class UserDtoToUserModelMapper implements Mapper<UserDTO, UserModel> {

    private final Mapper<CustomerDTO, CustomerModel> customerDtoToCustomerModelMapper;
    private final Mapper<RoleDTO, RoleModel> roleDtoToRoleModelMapper;

    public UserDtoToUserModelMapper(Mapper<CustomerDTO, CustomerModel> customerDtoToCustomerModelMapper,
                                    Mapper<RoleDTO, RoleModel> roleDtoToRoleModelMapper) {
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
