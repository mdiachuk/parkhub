package ua.com.parkhub.mappers.dtoToModel;

import org.springframework.stereotype.Component;
import ua.com.parkhub.dto.AuthUserDTO;
import ua.com.parkhub.mappers.Mapper;
import ua.com.parkhub.model.AuthUserModel;

@Component
public class AuthUserDTOtoAuthUserModelMapper implements Mapper<AuthUserDTO, AuthUserModel> {
    @Override
    public AuthUserModel transform(AuthUserDTO from) {
        AuthUserModel authUserModel = new AuthUserModel();
        authUserModel.setLastName(from.getLastName());
        authUserModel.setFirstName(from.getFirstName());
        authUserModel.setEmail(from.getEmail());
        return authUserModel;
    }
}
