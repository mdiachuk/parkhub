package ua.com.parkhub.service;

import ua.com.parkhub.model.UserModel;

public interface IAuthorizationService {

    UserModel loginUser(UserModel userModel);
}
