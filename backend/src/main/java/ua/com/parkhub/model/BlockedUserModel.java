package ua.com.parkhub.model;

import java.util.Date;

public class BlockedUserModel {

    private Long blockedUserId;
    private UserModel blockedUser;
    private Date blockingDate;

    public Long getBlockedUserId() {
        return blockedUserId;
    }

    public void setBlockedUserId(Long blockedUserId) {
        this.blockedUserId = blockedUserId;
    }

    public UserModel getBlockedUser() {
        return blockedUser;
    }

    public void setBlockedUser(UserModel blockedUser) {
        this.blockedUser = blockedUser;
    }

    public Date getBlockingDate() {
        return blockingDate;
    }

    public void setBlockingDate(Date blockingDate) {
        this.blockingDate = blockingDate;
    }
}
