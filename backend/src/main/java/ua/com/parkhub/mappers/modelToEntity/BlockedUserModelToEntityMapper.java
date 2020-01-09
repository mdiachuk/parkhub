package ua.com.parkhub.mappers.modelToEntity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.com.parkhub.exceptions.ParkHubException;
import ua.com.parkhub.mappers.Mapper;
import ua.com.parkhub.model.BlockedUserModel;
import ua.com.parkhub.persistence.entities.BlockedUser;

import java.sql.Date;

@Component
public class BlockedUserModelToEntityMapper implements Mapper<BlockedUserModel, BlockedUser> {

    private UserModelToEntityMapper userModelToEntityMapper;

    @Autowired
    public BlockedUserModelToEntityMapper(UserModelToEntityMapper userModelToEntityMapper) {
        this.userModelToEntityMapper = userModelToEntityMapper;
    }

    @Override
    public BlockedUser transform(BlockedUserModel model) {
        if(model == null) {
            throw new ParkHubException("BlockedUserModel to be mapped to BlockedUser entity is null.");
        }
        BlockedUser blockedUser = new BlockedUser();
        blockedUser.setBlockedUserId(model.getBlockedUserId());
        blockedUser.setBlockedUser(userModelToEntityMapper.transform(model.getBlockedUser()));
        blockedUser.setDate(new Date(model.getBlockingDate().getTime()));
        return blockedUser;
        
    }
}
