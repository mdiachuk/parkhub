package ua.com.parkhub.service;

import ua.com.parkhub.model.UserModel;

import java.util.Optional;

public interface IUserService {
    Optional<UserModel> findUserById(long Id);
    void changePassword(long id, String newPassword, UserModel userModel);
    void updateUser(long id, UserModel userUp);
}
