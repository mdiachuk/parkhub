package ua.com.parkhub.mappers.dtoToModel;

import org.springframework.stereotype.Component;
import ua.com.parkhub.dto.PasswordDTO;
import ua.com.parkhub.mappers.Mapper;
import ua.com.parkhub.model.UserModel;

@Component
public class PasswordDTOtoUserModelMapper implements Mapper<PasswordDTO, UserModel> {

    @Override
    public UserModel transform(PasswordDTO from) {
        if(from == null) {
            return null;
        }
        UserModel userModel = new UserModel();
        userModel.setPassword(from.getPassword());
        return userModel;
    }
}
