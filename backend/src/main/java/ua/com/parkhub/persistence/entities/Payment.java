package ua.com.parkhub.persistence.entities;

import javax.persistence.Entity;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Table(name = "payment",schema = "park_hub")
public class Payment implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private int price;

    @Column
    @NotNull
    private boolean isPaid = false;

    @Column
    @NotNull
    private boolean isCancelled = false;

    @OneToOne
    @JoinColumn(name = "booking_id")
    private Booking booking;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public boolean isPaid() {
        return isPaid;
    }

    public void setPaid(boolean paid) {
        isPaid = paid;
    }

    public boolean isCancelled() {
        return isCancelled;
    }

    public void setCancelled(boolean cancelled) {
        isCancelled = cancelled;
    }

    public Booking getBooking() {
        return booking;
    }

    public void setBooking(Booking booking) {
        this.booking = booking;
    }
}
