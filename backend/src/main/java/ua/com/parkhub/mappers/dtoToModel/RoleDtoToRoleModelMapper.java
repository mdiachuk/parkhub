package ua.com.parkhub.mappers.dtoToModel;

import org.springframework.stereotype.Component;
import ua.com.parkhub.dto.RoleDTO;
import ua.com.parkhub.exceptions.ParkHubException;
import ua.com.parkhub.mappers.Mapper;
import ua.com.parkhub.model.enums.RoleModel;

import java.util.Arrays;

@Component
public class RoleDtoToRoleModelMapper implements Mapper<RoleDTO, RoleModel> {

    @Override
    public RoleModel transform(RoleDTO roleDTO) {
        if (roleDTO == null) {
            return null;
        }
        RoleModel roleModel;
        switch (roleDTO) {
            case ADMIN:
                roleModel = RoleModel.ADMIN;
                roleModel.setId(roleDTO.getId());
                break;
            case USER:
                roleModel = RoleModel.USER;
                roleModel.setId(roleDTO.getId());
                break;
            case MANAGER:
                roleModel = RoleModel.MANAGER;
                roleModel.setId(roleDTO.getId());
                break;
            case PENDING:
                roleModel = RoleModel.PENDING;
                roleModel.setId(roleDTO.getId());
                break;
            default:
                throw new ParkHubException(String.format("Not known role name: %s. Role name may be one of the following: %s).", roleDTO.getRoleName(), Arrays.asList(RoleModel.values())));
        }
        return roleModel;
    }
}
