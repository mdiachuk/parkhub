package ua.com.parkhub.model;

import java.util.List;
import java.util.Objects;

public class Parking extends AbstractModel {

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
        if (!(o instanceof Parking)) return false;

        Parking parking = (Parking) o;

        if (slotsNumber != parking.slotsNumber) return false;
        if (tariff != parking.tariff) return false;
        if (isActive != parking.isActive) return false;
        if (!Objects.equals(id, parking.id)) return false;
        if (!Objects.equals(name, parking.name)) return false;
        if (!Objects.equals(address, parking.address)) return false;
        if (!Objects.equals(owner, parking.owner)) return false;
        return Objects.equals(slots, parking.slots);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + slotsNumber;
        result = 31 * result + tariff;
        result = 31 * result + (isActive ? 1 : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        result = 31 * result + (owner != null ? owner.hashCode() : 0);
        result = 31 * result + (slots != null ? slots.hashCode() : 0);
        return result;
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
