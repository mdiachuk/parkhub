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
        return Optional.of(new User(model.getFirstName(),
                model.getLastName(),
                model.getEmail(),
                model.getPassword(),
                userRoleMapper.toEntity(model.getUserRole()).get(),
                customerMapper.toEntity(model.getCustomer()).get()));
    }

    @Override
    public Optional<UserModel> toModel(User entity) {
        return Optional.of(new UserModel(customerMapper.toModel(entity.getCustomer()).get(),
                entity.getFirstName(),
                entity.getLastName(),
                entity.getEmail(),
                entity.getPassword(),
                userRoleMapper.toModel(entity.getRole()).get()));
    }
}
