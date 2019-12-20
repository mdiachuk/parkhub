package ua.com.parkhub.dto;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.hibernate.validator.constraints.UniqueElements;
import ua.com.parkhub.persistence.entities.Address;

import javax.validation.constraints.NotNull;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "parkingName",
        "slotsNumber",
        "tariff",
        "city",
        "street",
        "building"

})
public class ParkingRequestDTO {
    @NotNull
    @JsonProperty("parkingName")
    private String parkingName;

    @NotNull
    @JsonProperty("slotsNumber")
    private int slotsNumber;

    @NotNull
    @JsonProperty("tariff")
    private int tariff;


    @NotNull
    @JsonProperty("city")
    private String city;

    @NotNull
    @JsonProperty("street")
    private String street;

    @NotNull
    @JsonProperty("building")
    private String building;


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

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getBuilding() {
        return building;
    }

    public void setBuilding(String building) {
        this.building = building;
    }

}
