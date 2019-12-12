package ua.com.parkhub.backend.dao.impl;

import org.springframework.stereotype.Service;
import ua.com.parkhub.backend.dao.IUserDAO;
import ua.com.parkhub.backend.persistense.entities.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Service
public class UserDAO  implements IUserDAO {

    @PersistenceContext
    public EntityManager entityManager;

    @Override
    public String createUser(User user) {
        entityManager.persist(user);
        return null;
    }
}
