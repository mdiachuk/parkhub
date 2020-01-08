package ua.com.parkhub.persistence.impl;

import org.springframework.stereotype.Repository;
import ua.com.parkhub.exceptions.PermissionException;
import ua.com.parkhub.exceptions.StatusCode;
import ua.com.parkhub.mappers.Mapper;
import ua.com.parkhub.model.UserModel;
import ua.com.parkhub.persistence.entities.User;

import javax.persistence.Query;


@Repository
public class UserDAO extends ElementDAO<User, UserModel> {

    public UserDAO(Mapper<User, UserModel> entityToModel, Mapper<UserModel, User> modelToEntity) {
        super(User.class, modelToEntity, entityToModel);
    }

    public String haveEmail(String email) {
        Query query =
                emp.createQuery("SELECT id FROM User WHERE email=:email")
                        .setParameter("email", email).setMaxResults(1);

        if (query.getResultList().isEmpty()) {
            return "";
        } else {
            return query.getResultList().get(0).toString();
        }
    }


    public String havePhoneNumber(String phoneNumber) {
        Query query =
                emp.createQuery("SELECT id FROM Customer WHERE phone_number=:phoneNumber")
                        .setParameter("phoneNumber", phoneNumber).setMaxResults(1);

        if (query.getResultList().isEmpty()) {
            return "";
        } else {
            return query.getResultList().get(0).toString();
        }
    }

    public String findUserByCustomerId(String customerId) {

        if (customerId.length()==0){
            return "";
        } else {
            int id = Integer.parseInt(customerId);
            Query query =
                    emp.createQuery("SELECT id FROM User WHERE customer_id=:customerId")
                            .setParameter("customerId", id).setMaxResults(1);

            if (query.getResultList().isEmpty()) {
                return "";
            } else {
                return query.getResultList().get(0).toString();
            }
        }


    }
    public UserModel findUserByEmail(String email) { return findOneByFieldEqual("email", email).orElseThrow(() -> new PermissionException(StatusCode.NO_ACCOUNT_FOUND));}

}

