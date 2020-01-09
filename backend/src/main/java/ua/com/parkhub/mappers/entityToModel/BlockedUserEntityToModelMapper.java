package ua.com.parkhub.mappers.EntityToModel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.com.parkhub.mappers.Mapper;
import ua.com.parkhub.model.BlockedUserModel;
import ua.com.parkhub.persistence.entities.BlockedUser;

@Component
public class BlockedUserEntityToModelMapper implements Mapper<BlockedUser, BlockedUserModel> {

    private UserEntityToModelMapper userEntityToModelMapper;

    @Autowired
    public BlockedUserEntityToModelMapper(UserEntityToModelMapper userEntityToModelMapper) {
        this.userEntityToModelMapper = userEntityToModelMapper;
    }

    @Override
    public BlockedUserModel transform(BlockedUser entity) {
        if(entity == null) {
            return null;
//            throw new ParkHubException("BlockedUser entity to be converted to BlockedUserModel is null.");
        }
        BlockedUserModel model = new BlockedUserModel();
        model.setBlockedUserId(entity.getBlockedUserId());
        model.setBlockedUser(userEntityToModelMapper.transform(entity.getBlockedUser()));
        model.setBlockingDate(entity.getDate());
        return model;
    }
}
