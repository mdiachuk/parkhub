package ua.com.parkhub.persistence.entities;

import javax.persistence.*;
import java.util.Objects;

@javax.persistence.Entity
@Table(name = "customer"
        , schema="park_hub"
)
public class Customer implements ua.com.parkhub.persistence.entities.Entity {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column (name = "phone_number")
//    @Pattern(regexp = "^((\\+[1-9]?[0-9])|0)?[0-9][0-9]{10}$")
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

    public Long getId() {
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
