package ua.com.parkhub.service;

import ua.com.parkhub.dto.LoginDTO;
import ua.com.parkhub.dto.UserDTO;
import ua.com.parkhub.model.UserModel;

public interface AuthorizationService {

    UserModel loginUser(LoginDTO userDTO);
}
