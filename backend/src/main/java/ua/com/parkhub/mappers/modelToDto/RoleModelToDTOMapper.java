package ua.com.parkhub.mappers.modelToDto;

import org.springframework.stereotype.Component;
import ua.com.parkhub.dto.RoleDTO;
import ua.com.parkhub.exceptions.ParkHubException;
import ua.com.parkhub.mappers.Mapper;
import ua.com.parkhub.model.enums.RoleModel;

import java.util.Arrays;

@Component
public class RoleModelToDTOMapper implements Mapper<RoleModel, RoleDTO> {

    @Override
    public RoleDTO transform(RoleModel model) {
        RoleDTO roleDTO;
        switch (model) {
            case ADMIN:
                roleDTO = RoleDTO.ADMIN;
                roleDTO.setId(model.getId());
                break;
            case USER:
                roleDTO = RoleDTO.USER;
                roleDTO.setId(model.getId());
                break;
            case MANAGER:
                roleDTO = RoleDTO.MANAGER;
                roleDTO.setId(model.getId());
                break;
            case PENDING:
                roleDTO = RoleDTO.PENDING;
                roleDTO.setId(model.getId());
                break;
            default:
                throw new ParkHubException(String.format("Not known role name: %s. Role name may be one of the following: %s).", model.getRoleName(), Arrays.asList(RoleModel.values())));
        }
        return roleDTO;
    }
}
