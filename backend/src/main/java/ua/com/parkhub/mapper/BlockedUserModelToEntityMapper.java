package ua.com.parkhub.mapper;

import ua.com.parkhub.exceptions.ParkHubException;
import ua.com.parkhub.model.BlockedUserModel;
import ua.com.parkhub.persistence.entities.BlockedUser;
import ua.com.parkhub.persistence.entities.User;

public class BlockedUserModelToEntityMapper implements Mapper<BlockedUserModel, BlockedUser> {

    @Override
    public BlockedUser transform(BlockedUserModel model) {
        if(model == null) {
            throw new ParkHubException("BlockedUserModel to be mapped to BlockedUser entity is null.");
        }
        BlockedUser blockedUser = new BlockedUser();
        User user = new UserModelToEntityMapper().transform(model.getBlockedUser());
        blockedUser.setBlockedUserId(model.getBlockedUserId());
        blockedUser.setBlockedUser(user);
        blockedUser.setDate(blockedUser.getDate());
        return blockedUser;
    }
}
