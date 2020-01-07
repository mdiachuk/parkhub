package ua.com.parkhub.mappers.fromDtoToModel;

import org.springframework.stereotype.Component;
import ua.com.parkhub.dto.RoleDTO;
import ua.com.parkhub.mapper.Mapper;
import ua.com.parkhub.model.RoleModel;

@Component
public class RoleDtoToRoleModelMapper implements Mapper<RoleDTO, RoleModel> {
    @Override
    public RoleModel transform(RoleDTO from) {

        switch (from.getRoleName()) {
            case "ADMIN":
                return RoleModel.ADMIN;

            case "USER":
                return RoleModel.USER;

            case "MANAGER":
                return RoleModel.MANAGER;

            case "PENDING":
                return RoleModel.PENDING;

            default:
                return null;
        }


    }
}
