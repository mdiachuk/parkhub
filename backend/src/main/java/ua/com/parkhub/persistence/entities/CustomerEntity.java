package ua.com.parkhub.persistence.entities;

import javax.persistence.Entity;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "customer", schema = "park_hub")
public class CustomerEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "phone_number", length = 13)
    @Size(min = 13, max = 13)
    @NotNull
    private String phoneNumber;

    @Column
    @NotNull
    private boolean isActive = true;

    @OneToMany
    @JoinColumn(name = "customer_id")
    private List<BookingEntity> bookings;

    @OneToMany
    @JoinColumn(name = "author_id")
    private List<SupportTicketEntity> supportTickets;

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


    public List<BookingEntity> getBookings() {
        return bookings;
    }

    public void setBookings(List<BookingEntity> bookings) {
        this.bookings = bookings;
    }

    public List<SupportTicketEntity> getSupportTickets() {
        return supportTickets;
    }

    public void setSupportTickets(List<SupportTicketEntity> supportTickets) {
        this.supportTickets = supportTickets;
    }
}
