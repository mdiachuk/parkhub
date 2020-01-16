package ua.com.parkhub.dto;


import java.util.List;

public class ParkingWithSlotsDTO {

    private Long id;
    private String name;
    private String tariff;
    private String address;
    private List<SlotDTO> slots;

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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTariff() {
        return tariff;
    }

    public void setTariff(String tariff) {
        this.tariff = tariff;
    }

    public List<SlotDTO> getSlots() {
        return slots;
    }

    public void setSlots(List<SlotDTO> slots) {
        this.slots = slots;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("ParkingWithSlotsDTO" + ", id: ").append(id);
        sb.append(", name: ").append(name);
        sb.append(", address: ").append(address);
        sb.append(", tariff: ").append(tariff);
        sb.append(", slots: ").append(slots);
        return sb.toString();
    }
}
