package ua.com.parkhub.dto;

import java.util.Date;

public class BlockedUserDTO {

    private Long blockedUserId;
    private UserDTO blockedUser;
    private Date blockingDate;

    public Long getBlockedUserId() {
        return blockedUserId;
    }

    public void setBlockedUserId(Long blockedUserId) {
        this.blockedUserId = blockedUserId;
    }

    public UserDTO getBlockedUser() {
        return blockedUser;
    }

    public void setBlockedUser(UserDTO blockedUser) {
        this.blockedUser = blockedUser;
    }

    public Date getBlockingDate() {
        return blockingDate;
    }

    public void setBlockingDate(Date blockingDate) {
        this.blockingDate = blockingDate;
    }
}
