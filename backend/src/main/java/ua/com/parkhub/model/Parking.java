package ua.com.parkhub.model;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public class Parking implements Serializable, Cloneable {

    private Long id;
    private String name;
    private int slotsNumber;
    private int tariff;
    private boolean isActive;
    private Address address;
    private User owner;
    private List<Slot> slots;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public List<Slot> getSlots() {
        return slots;
    }

    public void setSlots(List<Slot> slots) {
        this.slots = slots;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Parking parking = (Parking) o;
        return slotsNumber == parking.slotsNumber &&
                tariff == parking.tariff &&
                isActive == parking.isActive &&
                Objects.equals(id, parking.id) &&
                Objects.equals(name, parking.name) &&
                Objects.equals(address, parking.address) &&
                Objects.equals(owner, parking.owner);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, slotsNumber, tariff, isActive, address, owner);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Parking" + ", id: ").append(id);
        sb.append(", name: ").append(name);
        sb.append(", slotsNumber: ").append(slotsNumber);
        sb.append(", tariff: ").append(tariff);
        sb.append(", isActive: ").append(isActive);
        sb.append(", address: ").append(address);
        sb.append(", owner: ").append(owner);
        sb.append(", slots: ").append(slots);
        return sb.toString();
    }
}
