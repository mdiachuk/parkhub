package ua.com.parkhub.persistence.impl;

import org.springframework.stereotype.Repository;
import ua.com.parkhub.mappers.Mapper;
import ua.com.parkhub.model.CustomerModel;
import ua.com.parkhub.model.PhoneEmailModel;
import ua.com.parkhub.model.UserModel;
import ua.com.parkhub.persistence.entities.User;

import java.util.List;
import java.util.Optional;


@Repository
public class UserDAO extends ElementDAO<User, UserModel> {

    public UserDAO(Mapper<User, UserModel> entityToModel, Mapper<UserModel, User> modelToEntity) {
        super(User.class, modelToEntity, entityToModel);
    }

    public Optional<UserModel> findUserByEmail(String email) {
        return findOneByFieldEqual("email", email);
    }

    public Optional<UserModel> findUserByCustomerId(Long id) {
        return findOneByFieldEqual("customer", id);
    }

    public List<UserModel> findUsersByRoleId(Long id) {
        return findManyByFieldEqual("role", id);
    }
    public CustomerModel setOauthUserPhone(PhoneEmailModel phoneEmailModel){
        UserModel user = findOneByFieldEqual("email", phoneEmailModel.getEmail()).get();
        CustomerModel customer = user.getCustomer();
        customer.setPhoneNumber(phoneEmailModel.getPhoneNumber());
        return  customer;
    }

}

