package ua.com.parkhub.service;

import ua.com.parkhub.model.UserModel;

public interface AuthorizationService {

    UserModel loginUser(UserModel userDTO);
}
