package ua.com.parkhub.mappers.modelToDto;

import org.springframework.stereotype.Component;
import ua.com.parkhub.dto.UserInfoDTO;
import ua.com.parkhub.exceptions.ParkHubException;
import ua.com.parkhub.mappers.Mapper;
import ua.com.parkhub.model.UserModel;

@Component
public class UserModelToUserInfoDTOMapper implements Mapper<UserModel, UserInfoDTO> {

    @Override
    public UserInfoDTO transform(UserModel from) {
        if(from == null) {
            return null;
        }
        UserInfoDTO userInfoDTO = new UserInfoDTO();
        userInfoDTO.setFirstName(from.getFirstName());
        userInfoDTO.setLastName(from.getLastName());
        userInfoDTO.setPhoneNumber(from.getCustomer().getPhoneNumber());
        userInfoDTO.setEmail(from.getEmail());
        return userInfoDTO;
    }
}
