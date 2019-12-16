package ua.com.parkhub.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import ua.com.parkhub.persistence.entities.Address;
import ua.com.parkhub.persistence.entities.User;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "parkingName",
        "slotsNumber",
        "tariff",
        "address",
        "ownerEmail"

})
public class ParkingDTO {

    @JsonProperty("parkingName")
    private String parkingName;

    @JsonProperty("slotsNumber")
    private int slotsNumber;

    @JsonProperty("tariff")
    private int tariff;


    @JsonProperty("address")
    private Address address;

    @JsonProperty("ownerEmail")
    private Long ownerEmail;

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

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Long getOwnerEmail() {
        return ownerEmail;
    }

    public void setOwnerEmail(Long ownerEmail) {
        this.ownerEmail = ownerEmail;
    }

//    public Long getOwnerId() {
//        return ownerId;
//    }
//
//    public void setOwnerId(Long ownerId) {
//        this.ownerId = ownerId;
//    }
}
