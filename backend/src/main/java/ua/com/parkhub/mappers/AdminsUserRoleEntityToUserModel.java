package ua.com.parkhub.mappers;

import org.springframework.stereotype.Component;
import ua.com.parkhub.model.AdminUserRoleModel;
import ua.com.parkhub.persistence.entities.UserRole;

@Component
public class AdminsUserRoleEntityToUserModel implements Mapper<UserRole, AdminUserRoleModel> {

    @Override
    public AdminUserRoleModel transformEtoM(UserRole from){
        return new AdminUserRoleModel(
                from.getId(),
                from.getRoleName()
        );
    }

    @Override
    public UserRole transformMtoE(AdminUserRoleModel from){
        return new UserRole(
                from.getId(),
                from.getRoleName()
        );
    }
}
