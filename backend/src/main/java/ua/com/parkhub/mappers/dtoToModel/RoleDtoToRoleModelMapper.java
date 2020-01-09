package ua.com.parkhub.mappers.dtoToModel;

import org.springframework.stereotype.Component;
import ua.com.parkhub.dto.RoleDTO;
import ua.com.parkhub.mappers.Mapper;
import ua.com.parkhub.model.RoleModel;

@Component
public class RoleDtoToRoleModelMapper implements Mapper<RoleDTO, RoleModel> {

    @Override
    public RoleModel transform(RoleDTO from) {
        return null;
    }
}
