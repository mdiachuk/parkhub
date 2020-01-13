package ua.com.parkhub.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "parkingName",
        "slotsNumber",
        "tariff",
        "city",
        "street",
        "building",
        "id"

})
public class ParkingRequestDTO {

    @NotNull(message = "Name of parking must not be null")
    @Pattern(regexp="^[a-zA-ZА-Яа-яйєї 0-9-]+$", message="Invalid name of parking")
    @JsonProperty("parkingName")
    private String parkingName;

    @NotNull(message = "Number of slots must not be null")
    @JsonProperty("slotsNumber")
    private int slotsNumber;

    @NotNull(message = "Tariff must not be null")
    @JsonProperty("tariff")
    private int tariff;

    @NotNull(message = "Name of city must not be null")
    @JsonProperty("city")
    private String city;

    @NotNull(message = "Name of street must not be null")
    @Pattern(regexp="^([a-zA-ZА-Яа-яйєї -]+)$", message="Invalid name of street")
    @JsonProperty("street")
    private String street;

    @NotNull(message = "Name of building must not be null")
    @Pattern(regexp="^([a-zA-ZА-Яа-яйєї 0-9-]+)$", message="Invalid number of building")
    @JsonProperty("building")
    private String building;

    @NotNull
    @JsonProperty("id")
    private Long id;
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}