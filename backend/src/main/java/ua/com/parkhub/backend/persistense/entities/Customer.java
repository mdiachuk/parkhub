package ua.com.parkhub.backend.persistense.entities;

import javax.persistence.*;
import java.util.Objects;

@Table(name = "customer")
public class Customer implements Entity {

    @Id
    @Column(name = "id")
    @GeneratedValue (strategy = GenerationType.AUTO)
    private long id;

    @Column (name = "phone_number")
    private String phoneNumber;

    @Column (name = "is_active")
    private boolean isActive;

    @OneToOne(mappedBy = "customer", cascade = CascadeType.ALL,
             fetch = FetchType.LAZY, optional = false)
    private User user;

    public Customer() {
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return id == customer.id &&
                isActive == customer.isActive &&
                Objects.equals(phoneNumber, customer.phoneNumber)
                && Objects.equals(user, customer.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, phoneNumber, isActive,
                user
        );
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", isActive=" + isActive +
                ", user=" + user + '}';
    }
}
