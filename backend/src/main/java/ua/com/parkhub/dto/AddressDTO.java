package ua.com.parkhub.dto;

public class AddressDTO {

    private String address;

    public AddressDTO(String address) {
        this.address = address;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
