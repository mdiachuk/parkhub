package ua.com.parkhub.service;

import ua.com.parkhub.model.UserModel;

import java.util.Optional;

public interface IUserService {
    void sendToken(String email, String type);
    void resendToken(String email, String type);
    void verifyEmail(String token);
    void resetPassword(String token, String password);
    boolean isLinkActive(String token);

    Optional<UserModel> findUserById(long Id);
    void changePassword(long id, String newPassword, UserModel userModel);
    void updateUser(long id, UserModel userUp);
}
