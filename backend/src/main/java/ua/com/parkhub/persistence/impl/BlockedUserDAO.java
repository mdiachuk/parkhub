package ua.com.parkhub.persistence.impl;

import org.springframework.stereotype.Repository;
import ua.com.parkhub.exceptions.PermissionException;
import ua.com.parkhub.persistence.entities.BlockedUser;
import ua.com.parkhub.persistence.entities.User;

import java.sql.Date;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Optional;

@Repository
public class BlockedUserDAO extends ElementDAO<BlockedUser> {

    public BlockedUserDAO() {
        super(BlockedUser.class);
    }

    private void activateUser(BlockedUser user){
        Optional<BlockedUser> optUser = findOneByFieldEqual("blocked_user_id", user.getBlocked_user().getId());
        if (optUser.isPresent()){
            Date blockingDate = optUser.get().getDate();
            if (blockingDate.toLocalDate().plus(24, ChronoUnit.HOURS).isAfter(LocalDate.now())){
                    deleteElement(optUser.get());
            } else {
                throw new PermissionException("Can not activate user: Less than 24 hours have passed.");
            }
        }
    }

//    public void blockUser(User user) {
//        if (!(findOneByFieldEqual("blocked_user", user.getId()).isPresent())) {
//            BlockedUser blockedUser = new BlockedUser();
//            blockedUser.setBlocked_user(user);
//            blockedUser.setDate(new Date(System.currentTimeMillis()));
//            addElement(blockedUser);
//        }
//    }
}
