package ua.com.parkhub.persistence.entities;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@javax.persistence.Entity
@Table(name = "customer", schema="park_hub"
)
public class Customer implements ua.com.parkhub.persistence.entities.Entity {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column (name = "phone_number")
    private String phoneNumber;

    @Column (name = "is_active")
    private boolean isActive;

    @OneToOne(mappedBy = "customer", cascade = CascadeType.ALL,
             fetch = FetchType.LAZY, optional = false)
    private User user;

    @OneToMany(mappedBy = "customer")
    private List<SupportTicket> supportTickets;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<SupportTicket> getSupportTickets() {
        return supportTickets;
    }

    public void setSupportTickets(List<SupportTicket> supportTickets) {
        this.supportTickets = supportTickets;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return isActive == customer.isActive &&
                Objects.equals(id, customer.id) &&
                Objects.equals(phoneNumber, customer.phoneNumber) &&
                Objects.equals(user, customer.user) &&
                Objects.equals(supportTickets, customer.supportTickets);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, phoneNumber, isActive, user, supportTickets);
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", isActive=" + isActive +
                ", user=" + user +
                ", supportTickets=" + supportTickets +
                '}';
    }
}
