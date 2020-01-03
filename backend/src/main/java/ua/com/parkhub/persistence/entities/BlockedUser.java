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
    private Long blocked_user_id;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User blocked_user;

    @Column(name = "blocking_date")
    private Date date;

    public User getBlocked_user() {
        return blocked_user;
    }

    public void setBlocked_user(User blocked_user) {
        this.blocked_user = blocked_user;
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
