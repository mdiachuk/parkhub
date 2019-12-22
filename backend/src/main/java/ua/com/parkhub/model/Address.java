package ua.com.parkhub.model;

public class Address extends AbstractModel {

    private Long id;
    private String city;
    private String street;
    private String building;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
        StringBuilder sb = new StringBuilder();
        sb.append("id: ").append(id);
        sb.append(", city: ").append(city);
        sb.append(", street: ").append(street);
        sb.append(", building: ").append(building);
        return sb.toString();
    }
}
