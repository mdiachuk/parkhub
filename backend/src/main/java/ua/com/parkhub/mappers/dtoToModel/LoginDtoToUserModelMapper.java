package ua.com.parkhub.mappers.dtoToModel;

import org.springframework.stereotype.Component;
import ua.com.parkhub.dto.LoginDTO;
import ua.com.parkhub.mappers.Mapper;
import ua.com.parkhub.model.UserModel;

@Component
public class LoginDtoToUserModelMapper implements Mapper<LoginDTO, UserModel> {

    @Override
    public UserModel transform(LoginDTO from) {
        if (from == null) {
            return null;
        }
        UserModel userModel = new UserModel();
        userModel.setEmail(from.getEmail());
        userModel.setPassword(from.getPassword());
        return userModel;
    }
}
