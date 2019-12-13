package ua.com.parkhub.persistence.entities;

import javax.persistence.Entity;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Table(name = "slot", schema = "park_hub")
public class Slot implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "slot_generator")
    @SequenceGenerator(name = "slot_generator", sequenceName = "park_hub.slot_id_seq", allocationSize = 1)
    private Long id;

    @Column
    @NotNull
    private String slotNumber;

    @Column
    @NotNull
    private boolean isReserved = false;

    @Column
    @NotNull
    private boolean isActive = true;

    @ManyToOne
    private Parking parking;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSlotNumber() {
        return slotNumber;
    }

    public void setSlotNumber(String slotNumber) {
        this.slotNumber = slotNumber;
    }

    public boolean isReserved() {
        return isReserved;
    }

    public void setReserved(boolean reserved) {
        isReserved = reserved;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public Parking getParking() {
        return parking;
    }

    public void setParking(Parking parking) {
        this.parking = parking;
    }
}
