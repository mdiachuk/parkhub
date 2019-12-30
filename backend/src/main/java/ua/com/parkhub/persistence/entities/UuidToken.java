package ua.com.parkhub.persistence.entities;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "uuid_token", schema = "park_hub")
public class UuidToken implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column
    @NotNull
    private String token;

    @Column(name = "expiration_date")
    @NotNull
    private LocalDateTime expirationDate = LocalDateTime.now().plusMinutes(10);

    @ManyToOne
    @JoinColumn(name = "token_type_id")
    private UuidTokenType uuidTokenType;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public LocalDateTime getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(LocalDateTime expirationDate) {
        this.expirationDate = expirationDate;
    }

    public UuidTokenType getUuidTokenType() {
        return uuidTokenType;
    }

    public void setUuidTokenType(UuidTokenType uuidTokenType) {
        this.uuidTokenType = uuidTokenType;
    }
}