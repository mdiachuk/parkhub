package ua.com.parkhub.service;

import ua.com.parkhub.dto.LoginDTO;
import ua.com.parkhub.dto.UserDTO;

public interface AuthorizationService {

    UserDTO loginUser(LoginDTO userDTO);
}
