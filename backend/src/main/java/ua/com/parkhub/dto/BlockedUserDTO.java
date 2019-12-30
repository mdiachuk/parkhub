package ua.com.parkhub.dto;

import java.util.Date;

public class BlockedUserDTO {

    private Long blocked_user_id;
    private UserDTO blocked_user;
    private Date blocking_date;

    public Long getBlocked_user_id() {
        return blocked_user_id;
    }

    public void setBlocked_user_id(Long blocked_user_id) {
        this.blocked_user_id = blocked_user_id;
    }

    public UserDTO getBlocked_user() {
        return blocked_user;
    }

    public void setBlocked_user(UserDTO blocked_user) {
        this.blocked_user = blocked_user;
    }

    public Date getBlocking_date() {
        return blocking_date;
    }

    public void setBlocking_date(Date blocking_date) {
        this.blocking_date = blocking_date;
    }
}
