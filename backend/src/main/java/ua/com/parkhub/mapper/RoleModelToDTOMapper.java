package ua.com.parkhub.mapper;

import org.springframework.stereotype.Component;
import ua.com.parkhub.dto.RoleDTO;
import ua.com.parkhub.exceptions.ParkHubException;
import ua.com.parkhub.model.RoleModel;

import java.util.Arrays;

@Component
public class RoleModelToDTOMapper implements Mapper<RoleModel, RoleDTO> {

    @Override
    public RoleDTO transform(RoleModel model) {
        switch (model) {
            case ADMIN:
                return RoleDTO.ADMIN;
            case USER:
                return RoleDTO.USER;
            case MANAGER:
                return RoleDTO.MANAGER;
            case PENDING:
                return RoleDTO.PENDING;
            default:
                throw new ParkHubException(String.format("Not known role name: %s. Role name may be one of the following: %s).", model.getRoleName(), Arrays.asList(RoleModel.values())));
        }
    }
}
