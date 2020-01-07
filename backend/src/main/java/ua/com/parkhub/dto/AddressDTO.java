package ua.com.parkhub.dto;

////<<<<<<< HEAD
//import java.io.Serializable;
//
//public class AddressDTO implements Serializable {
//
//    private static final long serialVersionUID = 1L;
//    private String city;
//    private String street;
//    private String building;
//
//    public String getCity() {
//        return city;
//    }
//
//    public void setCity(String city) {
//        this.city = city;
//    }
//
//    public String getStreet() {
//        return street;
//    }
//
//    public void setStreet(String street) {
//        this.street = street;
//    }
//
//    public String getBuilding() {
//        return building;
//    }
//
//    public void setBuilding(String building) {
//        this.building = building;
//    }
//
//    @Override
//    public String toString() {
//        StringBuilder sb = new StringBuilder();
//        sb.append("city :").append(city);
//        sb.append(", street: ").append(street);
//        sb.append(", building: ").append(building);
//        return sb.toString();
//=======
public class AddressDTO {

    private String address;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
