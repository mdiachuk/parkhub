package ua.com.parkhub.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ua.com.parkhub.dto.PasswordDTO;
import ua.com.parkhub.dto.UserInfoDTO;
import ua.com.parkhub.model.UserModel;
import ua.com.parkhub.persistence.impl.UserDAO;
import ua.com.parkhub.service.IUserService;

import java.util.Optional;

@Service
public class UserService implements IUserService {
    private UserDAO userDAO;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserDAO userDAO){
        this.userDAO = userDAO;
    }

    public Optional<UserModel> findUserById(long id){
        return userDAO.findElementById(id);
    }
    public void updateUser(UserInfoDTO userUp) {
        UserModel userModel = findUserById(userUp.getId()).get();
        if (userUp.getFirstName() != null){
            userModel.setFirstName(userUp.getFirstName());
        }
        if (userUp.getLastName() != null){
            userModel.setLastName(userUp.getLastName());
        }
        if (userUp.getEmail()!= null){
            userModel.setEmail(userUp.getEmail());
        }
        if (userUp.getPhoneNumber() != null){
            userModel.getCustomer().setPhoneNumber(userUp.getPhoneNumber());
        }
        userDAO.updateElement(userModel);
    }
    public void changePassword(PasswordDTO passwordDTO){
        UserModel user = findUserById(passwordDTO.getId()).get();
        System.out.println(passwordDTO.getPassword());
        System.out.println(user.getPassword());
        System.out.println(passwordDTO.getNewPassword());
        if (passwordDTO.getPassword().equals(user.getPassword())){
            user.setPassword(passwordDTO.getNewPassword());
            System.out.println(user.getPassword());
            userDAO.updateElement(user);
        } else {
            new RuntimeException();
        }
    }
}
