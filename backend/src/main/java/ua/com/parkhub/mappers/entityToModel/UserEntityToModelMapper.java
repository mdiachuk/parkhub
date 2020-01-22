package ua.com.parkhub.mappers.entityToModel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.com.parkhub.mappers.Mapper;
import ua.com.parkhub.model.CustomerModel;
import ua.com.parkhub.model.UserModel;
import ua.com.parkhub.model.enums.RoleModel;
import ua.com.parkhub.persistence.entities.Customer;
import ua.com.parkhub.persistence.entities.User;
import ua.com.parkhub.persistence.entities.UserRole;

@Component
public class UserEntityToModelMapper implements Mapper<User, UserModel> {

    private Mapper<Customer, CustomerModel> customerEntityToModelMapper;
    private Mapper<UserRole, RoleModel> roleEntityToModelMapper;

    @Autowired
    public UserEntityToModelMapper(Mapper<Customer, CustomerModel> customerEntityToModelMapper, Mapper<UserRole, RoleModel> roleEntityToModelMapper) {
        this.customerEntityToModelMapper = customerEntityToModelMapper;
        this.roleEntityToModelMapper = roleEntityToModelMapper;
    }

    @Override
    public UserModel transform(User from) {
        if (from == null){
            return null;
        }
        UserModel userModel = new UserModel();
        userModel.setEmail(from.getEmail());
        userModel.setFirstName(from.getFirstName());
        userModel.setLastName(from.getLastName());
        userModel.setId(from.getId());
        userModel.setPassword(from.getPassword());
        userModel.setCustomer(customerEntityToModelMapper.transform(from.getCustomer()));
        userModel.setNumberOfFailedPassEntering(from.getNumberOfFailedPassEntering());
        userModel.setRole(roleEntityToModelMapper.transform(from.getRole()));
        return userModel;
    }
}
