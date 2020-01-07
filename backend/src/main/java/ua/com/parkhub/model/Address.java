//package ua.com.parkhub.model;
//
//import java.util.Objects;
//
//public class Address extends AbstractModel {
//
//    private Long id;
//    private String city;
//    private String street;
//    private String building;
//
//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
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
//    protected Object clone() throws CloneNotSupportedException {
//        return super.clone();
//    }
//
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        Address address = (Address) o;
//        return Objects.equals(id, address.id) &&
//                Objects.equals(city, address.city) &&
//                Objects.equals(street, address.street) &&
//                Objects.equals(building, address.building);
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(id, city, street, building);
//    }
//
//    @Override
//    public String toString() {
//        StringBuilder sb = new StringBuilder();
//        sb.append("id: ").append(id);
//        sb.append(", city: ").append(city);
//        sb.append(", street: ").append(street);
//        sb.append(", building: ").append(building);
//        return sb.toString();
//    }
//}
