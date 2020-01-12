package ua.com.parkhub.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AddressGeoDTO {

    private String lat;
    private  String lon;

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLon() {
        return lon;
    }

    public void setLon(String lon) {
        this.lon = lon;
    }

    @Override
    public String toString() {
        return "AdressGeoDTO{" +
                "lat='" + lat + '\'' +
                ", lon='" + lon + '\'' +
                '}';
    }
}
