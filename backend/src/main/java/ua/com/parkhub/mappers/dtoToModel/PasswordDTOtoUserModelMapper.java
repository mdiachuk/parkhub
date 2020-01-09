package ua.com.parkhub.mappers.dtoToModel;

import org.springframework.stereotype.Component;
import ua.com.parkhub.dto.PasswordDTO;
import ua.com.parkhub.exceptions.ParkHubException;
import ua.com.parkhub.mappers.Mapper;
import ua.com.parkhub.model.UserModel;
@Component
public class PasswordDTOtoUserModelMapper implements Mapper<PasswordDTO, UserModel> {
    @Override
    public UserModel transform(PasswordDTO from) {
        if(from == null) {
            throw new ParkHubException("PsswordDTO to be mapped to UserModel is null.");
        }
        UserModel userModel = new UserModel();
        userModel.setPassword(from.getPassword());
        return userModel;
    }
}
