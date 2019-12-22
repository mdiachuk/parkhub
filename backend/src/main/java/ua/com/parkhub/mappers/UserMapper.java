package ua.com.parkhub.mappers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.com.parkhub.model.UserModel;
import ua.com.parkhub.persistence.entities.User;

import java.util.Optional;

@Component
public class UserMapper implements Mapper<User, UserModel> {
    private UserRoleMapper userRoleMapper;
    private CustomerMapper customerMapper;

    @Autowired
    public UserMapper(UserRoleMapper userRoleMapper, CustomerMapper customerMapper) {
        this.userRoleMapper = userRoleMapper;
        this.customerMapper = customerMapper;
    }

    @Override
    public Optional<User> toEntity(UserModel model) {
        User user = new User();
        user.setCustomer(customerMapper.toEntity(model.getCustomer()).get());
        user.setRole(userRoleMapper.toEntity(model.getUserRole()).get());
        user.setFirstName(model.getFirstName());
        user.setLastName(model.getLastName());
        user.setEmail(model.getEmail());
        user.setPassword(model.getPassword());
        return Optional.of(user);
    }

    @Override
    public Optional<UserModel> toModel(User entity) {
        UserModel userModel = new UserModel();
        userModel.setCustomer(customerMapper.toModel(entity.getCustomer()).get());
        userModel.setUserRole(userRoleMapper.toModel(entity.getRole()).get());
        userModel.setFirstName(entity.getFirstName());
        userModel.setLastName(entity.getLastName());
        userModel.setEmail(entity.getEmail());
        userModel.setPassword(entity.getPassword());
        return Optional.of(userModel);
    }
}
