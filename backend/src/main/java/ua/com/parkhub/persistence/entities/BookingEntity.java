package ua.com.parkhub.persistence.entities;

import javax.persistence.Entity;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "booking", schema = "park_hub")
public class BookingEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 8)
    @Size(min = 8, max = 8)
    @NotNull
    private String carNumber;

    @Column(name = "check_in")
    @NotNull
    private LocalDateTime checkIn = LocalDateTime.now();

    @Column(name = "check_out")
    private LocalDateTime checkOut;

    @Column
    @NotNull
    private boolean isActive;

    @ManyToOne
    private CustomerEntity customer;

    @OneToOne
    @JoinColumn(name = "slot_id")
    private SlotEntity slot;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCarNumber() {
        return carNumber;
    }

    public void setCarNumber(String carNumber) {
        this.carNumber = carNumber;
    }

    public LocalDateTime getCheckIn() {
        return checkIn;
    }

    public void setCheckIn(LocalDateTime checkIn) {
        this.checkIn = checkIn;
    }

    public LocalDateTime getCheckOut() {
        return checkOut;
    }

    public void setCheck_out(LocalDateTime checkOut) {
        this.checkOut = checkOut;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public CustomerEntity getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerEntity customer) {
        this.customer = customer;
    }

    public SlotEntity getSlot() {
        return slot;
    }

    public void setSlot(SlotEntity slot) {
        this.slot = slot;
    }
}
