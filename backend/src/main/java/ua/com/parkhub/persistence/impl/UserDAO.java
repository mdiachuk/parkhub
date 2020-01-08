package ua.com.parkhub.persistence.impl;

import org.springframework.stereotype.Repository;
import ua.com.parkhub.mappers.Mapper;
import ua.com.parkhub.model.UserModel;
import ua.com.parkhub.persistence.entities.User;

import javax.persistence.Query;
import java.util.List;
import java.util.Optional;


@Repository
public class UserDAO extends ElementDAO<User, UserModel> {

    public UserDAO(Mapper<User, UserModel> entityToModel, Mapper<UserModel, User> modelToEntity) {
        super(User.class, modelToEntity, entityToModel);
    }

    /**
     * Check have this email in DB, and from DB return id of User
     * if this email have in DB
     *
     * @param email - User email
     * @return return id User
     */
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

    /**
     * Check have this phone Number in DB, and from DB return id of Customer
     * if this email have in DB
     *
     * @param phoneNumber - phoneNumber email
     * @return id Customer
     */

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

    /**
     * Find User by Customer is
     *
     * @param customerId Customer id you can find use phone Number
     *                   and use
     * @return id User
     * @method havePhoneNumber
     */
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

    public Optional<UserModel> findUserByEmail(String email) {
        return findOneByFieldEqual("email", email);
    }

    public Optional<UserModel> findUserByCustomerId(Long id) {
        return findOneByFieldEqual("customer", id);
    }

    public List<UserModel> findUsersByRoleId(Long id) {
        return findManyByFieldEqual("role", id);
    }
}

