package ua.com.parkhub.mappers.entityToModel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.com.parkhub.mappers.Mapper;
import ua.com.parkhub.model.UserModel;
import ua.com.parkhub.persistence.entities.User;

@Component
public class UserEntityToModelMapper implements Mapper<User, UserModel> {

    private CustomerEntityToModelMapper customerEntityToModelMapper;
    private RoleEntityToModelMapper roleEntityToModelMapper;

    @Autowired
    public UserEntityToModelMapper(CustomerEntityToModelMapper customerEntityToModelMapper, RoleEntityToModelMapper roleEntityToModelMapper) {
        this.customerEntityToModelMapper = customerEntityToModelMapper;
        this.roleEntityToModelMapper = roleEntityToModelMapper;
    }

    @Override
    public UserModel transform(User entity) {
        UserModel userModel = new UserModel();
        userModel.setEmail(entity.getEmail());
        userModel.setFirstName(entity.getFirstName());
        userModel.setLastName(entity.getLastName());
        userModel.setId(entity.getId());
        userModel.setPassword(entity.getPassword());
        userModel.setCustomer(customerEntityToModelMapper.transform(entity.getCustomer()));
        userModel.setNumberOfFailedPassEntering(entity.getNumberOfFailedPassEntering());
        // set TICKETS
        userModel.setRole(roleEntityToModelMapper.transform(entity.getRole()));
        return userModel;
    }
}
