package ua.com.parkhub.mappers.dtoToModel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.com.parkhub.dto.ManagerRegistrationDataDTO;
import ua.com.parkhub.mappers.Mapper;
import ua.com.parkhub.model.ManagerRegistrationDataModel;

@Component
public class ManagerRegistrationRequestDtoToModel implements Mapper<ManagerRegistrationDataDTO, ManagerRegistrationDataModel> {

    UserDtoToUserModelMapper userDtoToUserModelMapper;

    @Autowired
    public ManagerRegistrationRequestDtoToModel(UserDtoToUserModelMapper userDtoToUserModelMapper) {
        this.userDtoToUserModelMapper = userDtoToUserModelMapper;
    }

    @Override
    public ManagerRegistrationDataModel transform(ManagerRegistrationDataDTO from) {
        ManagerRegistrationDataModel managerRegistrationDataModel = new ManagerRegistrationDataModel();
        managerRegistrationDataModel.setUser(userDtoToUserModelMapper.transform(from.getUser()));
        managerRegistrationDataModel.setCompanyName(from.getCompanyName());
        managerRegistrationDataModel.setUsreouCode(from.getUsreouCode());
        managerRegistrationDataModel.setComment(from.getComment());
        return managerRegistrationDataModel;
    }
}
