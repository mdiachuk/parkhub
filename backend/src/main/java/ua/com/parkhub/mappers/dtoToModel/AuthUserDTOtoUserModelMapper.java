package ua.com.parkhub.mappers.dtoToModel;

import org.springframework.stereotype.Component;
import ua.com.parkhub.dto.AuthUserDTO;
import ua.com.parkhub.mappers.Mapper;
import ua.com.parkhub.model.UserModel;

@Component
public class AuthUserDTOtoUserModelMapper implements Mapper<AuthUserDTO, UserModel> {

    @Override
    public UserModel transform(AuthUserDTO from) {
        if (from == null) {
            return null;
        }
        UserModel userModel = new UserModel();
        userModel.setLastName(from.getLastName());
        userModel.setFirstName(from.getFirstName());
        userModel.setEmail(from.getEmail());
        return userModel;
    }
}
