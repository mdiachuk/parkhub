package ua.com.parkhub.service;
import org.springframework.stereotype.Service;
import ua.com.parkhub.dto.AdminDTO;
import ua.com.parkhub.persistence.entities.User;
import ua.com.parkhub.persistence.entities.UserRole;
import ua.com.parkhub.persistence.impl.UserDAO;

@Service
public class AdminService  {
    public UserDAO userDAO;


    public AdminService(UserDAO userDAO) {
        this.userDAO = userDAO;

    }

    public User findUserById(long id){
        return userDAO.findElementById(id);
    }

    public void setRole(Long id, UserRole role){
        User targetUser = userDAO.findElementById(id);
        targetUser.setRole(role);
        userDAO.updateElement(targetUser);
    }
    public String getRole(long id){
        User targetUser = userDAO.findElementById(id);
        return targetUser.getRole().getRoleName();
    }

    public String getFirstName(long id){
        User targetUser = userDAO.findElementById(id);
        return targetUser.getFirstName();
    }

    /*public long getId(long id){
        User targetUser = userDAO.findElementById(id);
        return targetUser.getId();
    }*/
}

