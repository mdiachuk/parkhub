package ua.com.parkhub.service;

import ua.com.parkhub.model.UserModel;

import java.util.Optional;

public interface IUserService {
    Optional<UserModel> findUserById(long Id);
}
