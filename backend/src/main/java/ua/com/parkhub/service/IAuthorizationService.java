package ua.com.parkhub.service;

import ua.com.parkhub.dto.LoginDTO;
import ua.com.parkhub.dto.UserDTO;

public interface IAuthorizationService {

    UserDTO loginUser(LoginDTO userDTO);
}
