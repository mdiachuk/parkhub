
package ua.com.parkhub.persistence.impl;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ua.com.parkhub.exceptions.PermissionException;
import ua.com.parkhub.exceptions.StatusCode;
import ua.com.parkhub.mappers.Mapper;
import ua.com.parkhub.model.BlockedUserModel;
import ua.com.parkhub.model.UserModel;
import ua.com.parkhub.persistence.entities.BlockedUser;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@Repository
public class BlockedUserDAO extends ElementDAO<BlockedUser, BlockedUserModel> {

    public BlockedUserDAO(Mapper<BlockedUser, BlockedUserModel> entityToModel, Mapper<BlockedUserModel, BlockedUser> modelToEntity) {
        super(BlockedUser.class, modelToEntity, entityToModel);
    }

    @Transactional
    public void activateUser(UserModel user) {
        BlockedUserModel blockedUser = findOneByFieldEqual("blockedUser", user.getId())
                .orElseThrow(() -> new PermissionException(StatusCode.NO_BLOCKED_USER));
        deleteElement(blockedUser);
    }

    public Boolean canActivate(UserModel user) {
        BlockedUserModel blockedUser = findOneByFieldEqual("blockedUser", user.getId())
                .orElseThrow(() -> new PermissionException(StatusCode.NO_BLOCKED_USER));
            Date blockingDate = blockedUser.getBlockingDate();
            Instant instant = Instant.ofEpochMilli(blockingDate.getTime());
            if (LocalDate.now().minus(1, ChronoUnit.DAYS).isAfter(LocalDateTime.ofInstant(instant, ZoneId.systemDefault())
                    .toLocalDate())) {
                return true;
            } else {
                return false;
            }
    }

    public Boolean isBlocked(UserModel user) {
        if (findOneByFieldEqual("blockedUser", user.getId()).isPresent()) {
            return true;
        } else {
            return false;
        }
    }

    @Transactional
    public void blockUser(UserModel user) {
        BlockedUserModel blockedUser = new BlockedUserModel();
        blockedUser.setBlockedUser(user);
        blockedUser.setBlockingDate(new Date(System.currentTimeMillis()));
        addElement(blockedUser);
    }
}
