package ua.com.parkhub.persistence.entities;

import javax.persistence.Entity;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "customer", schema = "park_hub")
public class Customer implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "customer_generator")
    @SequenceGenerator(name = "customer_generator", sequenceName = "park_hub.customer_id_seq", allocationSize = 1)
    private Long id;

    @Column
    @NotNull
    @Size(min = 5, max = 50)
    private String phoneNumber;

    @Column
    @NotNull
    private boolean isActive = true;

    @OneToMany
    @JoinColumn(name = "customer_id")
    private List<Booking> bookings;

    @OneToMany
    @JoinColumn(name = "author_id")
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

    public List<Booking> getBookings() {
        return bookings;
    }

    public void setBookings(List<Booking> bookings) {
        this.bookings = bookings;
    }

    public List<SupportTicket> getSupportTickets() {
        return supportTickets;
    }

    public void setSupportTickets(List<SupportTicket> supportTickets) {
        this.supportTickets = supportTickets;
    }

}
