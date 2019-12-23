package ua.com.parkhub.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.com.parkhub.persistence.entities.User;
import ua.com.parkhub.persistence.impl.CustomerDAO;
import ua.com.parkhub.persistence.impl.UserDAO;


@Service
@Transactional
public class SingUpService {

    @Autowired
    public UserDAO userDAO;

    @Autowired
    public CustomerDAO customerDAO;
    /**
     * To create new user
     * @param user
     * @return false - if user not create;
     *         true - if user create
     */
    public boolean createUser(User user) {

        if (emptyField(user)){
            String haveEmail = userDAO.haveEmail(user.getEmail());
            String havePhoneNumber = userDAO.havePhoneNumber(user.getCustomer().getPhoneNumber());
            String customerId = userDAO.findUserByCustomerId(havePhoneNumber);

            if (haveEmail.length() != 0) {
                return false;
            } else {
                if (havePhoneNumber.length() != 0) {
                    if (customerId.length() != 0) {
                        return false;
                    } else {
                        return addUser(user);
                    }
                } else  {
                    return addUser(user);
                }
            }
        } else {
            return false;
        }

    }

    /**
     * If some fild is empty, new User can`t create account
     * @param user
     * @return false - if have empty field;
     *         true - if all field not empty
     */
    private boolean emptyField (User user) {
            return (user.getCustomer().getPhoneNumber().length()==0||
                    user.getEmail().length()==0||
                    user.getName().length()==0||
                    user.getPass().length()==0||
                    user.getSecondname().length()==0||
                    user.getRole().getId()!=1)?false:true;
    }

    private boolean addUser(User user) {
        user.getCustomer().setActive(true);
        userDAO.addElement(user);
        return true;

    }

}
