package ua.com.parkhub.persistence.entities;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Table(name = "address", schema = "park_hub")
public class Address implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "booking_generator")
    @SequenceGenerator(name="booking_generator", sequenceName = "park_hub.booking_id_seq", allocationSize = 1)
    private Long id;

    @Column
    @NotNull
    private String city;

    @Column
    @NotNull
    private String street;

    @Column
    @NotNull
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
}
