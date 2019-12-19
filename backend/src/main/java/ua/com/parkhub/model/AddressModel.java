package ua.com.parkhub.model;

public class AddressModel {

    private String city;
    private String street;
    private String building;

    public AddressModel(){
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

    @Override
    public String toString() {
        return street + " " + building + ", " + city;
    }

    //SOME BUSINESS LOGIC
}
