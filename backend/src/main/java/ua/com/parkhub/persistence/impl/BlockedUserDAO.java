//
//package ua.com.parkhub.persistence.impl;
//
//import org.springframework.stereotype.Repository;
//import org.springframework.transaction.annotation.Transactional;
//import ua.com.parkhub.exceptions.PermissionException;
//import ua.com.parkhub.persistence.entities.BlockedUser;
//import ua.com.parkhub.persistence.entities.User;
//
//import java.sql.Date;
//import java.time.LocalDate;
//import java.time.temporal.*;
//import java.util.Optional;
//
//@Repository
//public class BlockedUserDAO extends ElementDAO<BlockedUser> {
//
//    public BlockedUserDAO() {
//        super(BlockedUser.class);
//    }
//
//    @Transactional
//    public void activateUser(User user) {
//        Optional<BlockedUser> blockedUser = findOneByFieldEqual("blockedUser", user.getId());
//        deleteElement(blockedUser.get());
//    }
//
//    public Boolean canActivate(User user) {
//        Optional<BlockedUser> blockedUser = findOneByFieldEqual("blockedUser", user.getId());
//        if (isBlocked(user)) {
//            Date blockingDate = blockedUser.get().getDate();
//            if (LocalDate.now().minus(1, ChronoUnit.DAYS).isAfter(blockingDate.toLocalDate())) {
//                return true;
//            } else {
//                return false;
//            }
//        }
//        throw new PermissionException("User is not blocked.");
//    }
//
//    public Boolean isBlocked(User user) {
//        if (findOneByFieldEqual("blockedUser", user.getId()).isPresent()) {
//            return true;
//        } else {
//            return false;
//        }
//    }
//
//    @Transactional
//    public void blockUser(User user) {
//            BlockedUser blockedUser = new BlockedUser();
//            blockedUser.setBlockedUser(user);
//            blockedUser.setDate(new Date(System.currentTimeMillis()));
//            addElement(blockedUser);
//    }
//}
