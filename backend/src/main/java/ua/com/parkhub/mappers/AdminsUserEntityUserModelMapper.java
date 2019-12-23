package ua.com.parkhub.mappers;

import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import ua.com.parkhub.model.AdminUserModel;
import ua.com.parkhub.persistence.entities.User;

public class AdminsUserEntityUserModelMapper implements Mapper<AdminUserModel, User> {

    @Override
    public AdminUserModel transformMtoE(User from) {
        return new AdminUserModel(
                from.getId(),
                from.getFirstName(),
                from.getRole()
        );
    }

    @Override
    public User transformEtoM(AdminUserModel from) {
        return new User(
                from.getId(),
                from.getFirstName(),
                from.getRole()
        );
    }


}
