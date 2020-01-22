package ua.com.parkhub.mappers.modelToEntity;

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
public class UserModelToEntityMapper implements Mapper<UserModel, User> {

    private Mapper<CustomerModel, Customer> customerModelToEntityMapper;
    private Mapper<RoleModel, UserRole> roleModelToEntityMapper;

    @Autowired
    public UserModelToEntityMapper(Mapper<CustomerModel, Customer> customerModelToEntityMapper,
                                   Mapper<RoleModel, UserRole> roleModelToEntityMapper) {
        this.customerModelToEntityMapper = customerModelToEntityMapper;
        this.roleModelToEntityMapper = roleModelToEntityMapper;
    }

    @Override
    public User transform(UserModel from) {
        if(from == null) {
            return null;
        }
        User user = new User();
        user.setId(from.getId());
        user.setCustomer(customerModelToEntityMapper.transform(from.getCustomer()));
        user.setEmail(from.getEmail());
        user.setFirstName(from.getFirstName());
        user.setLastName(from.getLastName());
        user.setPassword(from.getPassword());
        user.setNumberOfFailedPassEntering(from.getNumberOfFailedPassEntering());
        user.setRole(roleModelToEntityMapper.transform(from.getRole()));
        return user;
    }
}
