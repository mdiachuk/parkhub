package ua.com.parkhub.persistence.impl;

import org.springframework.stereotype.Repository;
import ua.com.parkhub.mapper.ParkingMapper;
import ua.com.parkhub.persistence.entities.User;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Root;
import java.util.List;


@Repository
public class UserDAO extends ElementDAO<User> {

    public UserDAO() {
        super(User.class);
    }
    public User findElementByEmail(String email){
        CriteriaBuilder criteriaBuilder = emp.getCriteriaBuilder();
        CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);
        Root<User> root = criteriaQuery.from(User.class);
        criteriaQuery.select(root);


        ParameterExpression<String> params = criteriaBuilder.parameter(String.class);
        criteriaQuery.where(criteriaBuilder.equal(root.get("email"), params));

        TypedQuery<User> query = emp.createQuery(criteriaQuery);
        query.setParameter(params, email);
        //User queryResult;// = query.getSingleResult();
        List<User> results = query.getResultList();
        if (results.isEmpty()) {
            return null; // handle no-results case
        } else {
            return results.get(0);
        }//decide what to do with npe

    }

}

