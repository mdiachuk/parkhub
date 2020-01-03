package ua.com.parkhub.mapper;

import ua.com.parkhub.exceptions.ParkHubException;
import ua.com.parkhub.model.BlockedUserModel;
import ua.com.parkhub.model.UserModel;
import ua.com.parkhub.persistence.entities.BlockedUser;

public class BlockedUserEntityToModelMapper implements Mapper<BlockedUser, BlockedUserModel> {

    @Override
    public BlockedUserModel transform(BlockedUser entity) {
        if(entity == null) {
            throw new ParkHubException("BlockedUser entity to be converted to BlockedUserModel is null.");
        }
        BlockedUserModel model = new BlockedUserModel();
        UserModel userModel = new UserEntityToModelMapper().transform(entity.getBlockedUser());
        model.setBlockedUserId(entity.getBlockedUserId());
        model.setBlockedUser(userModel);
        model.setBlockingDate(entity.getDate());
        return model;
    }
}
