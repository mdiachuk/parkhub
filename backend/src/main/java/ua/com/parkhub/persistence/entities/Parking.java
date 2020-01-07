package ua.com.parkhub.persistence.entities;

import javax.persistence.Entity;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "parking",schema = "park_hub")
public class Parking {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "parking_generator")
    @SequenceGenerator(name="parking_generator", sequenceName = "park_hub.parking_id_seq", allocationSize = 1)
    private Long id;

    @Column
    @NotNull
    private String parkingName;

    @Column
    @NotNull
    private int slotsNumber;

    @Column
    @NotNull
    private int tariff;

    @Column
    private boolean isActive = true;

    @OneToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "address_id")
    private Address address;

    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "parking_owner_id")
    private User owner;

    @OneToMany
    @JoinColumn(name = "parking_id")
    private Set<Slot> slots;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getParkingName() {
        return parkingName;
    }

    public void setParkingName(String parkingName) {
        this.parkingName = parkingName;
    }

    public int getSlotsNumber() {
        return slotsNumber;
    }

    public void setSlotsNumber(int slotsNumber) {
        this.slotsNumber = slotsNumber;
    }

    public int getTariff() {
        return tariff;
    }

    public void setTariff(int tariff) {
        this.tariff = tariff;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public Set<Slot> getSlots() {
        return slots;
    }

    public void setSlots(Set<Slot> slots) {
        this.slots = slots;
    }
}
