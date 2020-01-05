package ua.com.parkhub.mappers;

import org.springframework.stereotype.Component;
import ua.com.parkhub.dto.RoleDTO;
import ua.com.parkhub.exceptions.ParkHubException;
import ua.com.parkhub.model.RoleModel;

import java.util.Arrays;

@Component
public class RoleDtoToRoleModelMapper implements Mapper<RoleDTO, RoleModel> {

    @Override
    public RoleModel transform(RoleDTO roleDTO) {
        switch (roleDTO) {
            case ADMIN:
                return RoleModel.ADMIN;
            case USER:
                return RoleModel.USER;
            case MANAGER:
                return RoleModel.MANAGER;
            case PENDING:
                return RoleModel.PENDING;
            default:
                throw new ParkHubException(String.format("Not known role name: %s. Role name may be one of the following: %s).", roleDTO.getRoleName(), Arrays.asList(RoleModel.values())));
        }
    }
}
