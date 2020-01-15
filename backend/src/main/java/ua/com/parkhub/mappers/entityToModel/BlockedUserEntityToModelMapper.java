package ua.com.parkhub.mappers.entityToModel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.com.parkhub.mappers.Mapper;
import ua.com.parkhub.model.BlockedUserModel;
import ua.com.parkhub.model.UserModel;
import ua.com.parkhub.persistence.entities.BlockedUser;
import ua.com.parkhub.persistence.entities.User;

@Component
public class BlockedUserEntityToModelMapper implements Mapper<BlockedUser, BlockedUserModel> {

    private Mapper<User, UserModel> userEntityToModelMapper;

    @Autowired
    public BlockedUserEntityToModelMapper(Mapper<User, UserModel> userEntityToModelMapper) {
        this.userEntityToModelMapper = userEntityToModelMapper;
    }

    @Override
    public BlockedUserModel transform(BlockedUser entity) {
        if(entity == null) {
            return null;
        }
        BlockedUserModel model = new BlockedUserModel();
        model.setBlockedUserId(entity.getBlockedUserId());
        model.setBlockedUser(userEntityToModelMapper.transform(entity.getBlockedUser()));
        model.setBlockingDate(entity.getDate());
        return model;
    }
}
