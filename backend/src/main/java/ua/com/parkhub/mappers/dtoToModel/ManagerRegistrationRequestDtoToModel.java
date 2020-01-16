package ua.com.parkhub.mappers.dtoToModel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.com.parkhub.dto.ManagerRegistrationDataDTO;
import ua.com.parkhub.dto.UserDTO;
import ua.com.parkhub.mappers.Mapper;
import ua.com.parkhub.model.ManagerRegistrationDataModel;
import ua.com.parkhub.model.UserModel;

@Component
public class ManagerRegistrationRequestDtoToModel implements Mapper<ManagerRegistrationDataDTO, ManagerRegistrationDataModel> {

    private Mapper<UserDTO, UserModel> userDtoToUserModelMapper;

    @Autowired
    public ManagerRegistrationRequestDtoToModel(Mapper<UserDTO, UserModel> userDtoToUserModelMapper) {
        this.userDtoToUserModelMapper = userDtoToUserModelMapper;
    }

    @Override
    public ManagerRegistrationDataModel transform(ManagerRegistrationDataDTO from) {
        if (from == null) {
            return null;
        }
        ManagerRegistrationDataModel managerRegistrationDataModel = new ManagerRegistrationDataModel();
        managerRegistrationDataModel.setUser(userDtoToUserModelMapper.transform(from.getUser()));
        managerRegistrationDataModel.setCompanyName(from.getCompanyName());
        managerRegistrationDataModel.setUsreouCode(from.getUsreouCode());
        managerRegistrationDataModel.setComment(from.getComment());
        return managerRegistrationDataModel;
    }
}
