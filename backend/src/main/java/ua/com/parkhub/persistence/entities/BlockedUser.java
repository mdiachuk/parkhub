package ua.com.parkhub.persistence.entities;

import javax.persistence.*;
import javax.persistence.Entity;
import java.io.Serializable;
import java.sql.Date;

@Entity
@Table(name = "blocked_user", schema = "park_hub")
public class BlockedUser implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long blockedUserId;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User blockedUser;

    @Column(name = "blocking_date")
    private Date date;

    public Long getBlockedUserId() {
        return blockedUserId;
    }

    public void setBlockedUserId(Long blockedUserId) {
        this.blockedUserId = blockedUserId;
    }

    public User getBlockedUser() {
        return blockedUser;
    }

    public void setBlockedUser(User blockedUser) {
        this.blockedUser = blockedUser;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public BlockedUser() {
    }
}
