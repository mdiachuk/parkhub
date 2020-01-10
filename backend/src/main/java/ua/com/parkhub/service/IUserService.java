package ua.com.parkhub.service;

import ua.com.parkhub.model.UserModel;
import ua.com.parkhub.model.UuidTokenType;

import java.util.Optional;

public interface IUserService {
    void sendToken(String email, UuidTokenType type);
    void resendToken(String email, UuidTokenType type);
    void verifyEmail(String token);
    void resetPassword(String token, String password);
    boolean isLinkActive(String token);

    Optional<UserModel> findUserById(long Id);
    void changePassword(long id, String newPassword, UserModel userModel);
    void updateUser(long id, UserModel userUp);
}
