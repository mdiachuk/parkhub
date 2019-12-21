package ua.com.parkhub.dto;

public class DescribedParkingDTO {

    private int slotsNumber;
    private int tariff;

    public DescribedParkingDTO(int slotsNumber, int tariff) {
        this.slotsNumber = slotsNumber;
        this.tariff = tariff;
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
}
