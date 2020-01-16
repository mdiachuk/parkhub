package ua.com.parkhub.mappers.dtoToModel;

import org.springframework.stereotype.Component;
import ua.com.parkhub.dto.UserInfoDTO;
import ua.com.parkhub.mappers.Mapper;
import ua.com.parkhub.model.CustomerModel;
import ua.com.parkhub.model.UserModel;

@Component
public class UserInfoDTOtoUserModelMapper implements Mapper<UserInfoDTO, UserModel> {

    @Override
    public UserModel transform(UserInfoDTO from) {
        if(from == null) {
            return null;
        }
        UserModel userModel = new UserModel();
        userModel.setEmail(from.getEmail());
        userModel.setFirstName(from.getFirstName());
        userModel.setLastName(from.getLastName());
        CustomerModel customerModel = new CustomerModel();
        customerModel.setPhoneNumber(from.getPhoneNumber());
        userModel.setCustomer(customerModel);
        return  userModel;
    }
}
