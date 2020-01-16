package ua.com.parkhub.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import ua.com.parkhub.dto.AddressGeoDTO;

import java.util.HashMap;
import java.util.Map;

@Service
public class AddressGeoService {
    private static final Logger LOGGER = LoggerFactory.getLogger(AddressGeoService.class);

    private RestTemplate restTemplate;
    private static final double EARTH_RADIUS = 6372795;


    public Map<String, String> getLatLon(String query) {

        HashMap hashMap = new HashMap();
        restTemplate = new RestTemplate();
        String url = "https://nominatim.openstreetmap.org/search.php?q=" +
                getUrl(query) + "&format=json";
        ResponseEntity<AddressGeoDTO[]> adressGeoDTO = null;

        try {
            adressGeoDTO = restTemplate.getForEntity(url, AddressGeoDTO[].class);

            hashMap.put("lat", adressGeoDTO.getBody()[0].getLat());
            hashMap.put("lon", adressGeoDTO.getBody()[0].getLon());
            return hashMap;
        } catch (ArrayIndexOutOfBoundsException e) {
            LOGGER.error("Not found address ->" + query + "!" + e);
        }
        catch (RestClientException e1 ){
            LOGGER.error("Connection timed out ->"+e1);
        }

        hashMap.put("lat", "-500");
        hashMap.put("lon", "-500");
        return hashMap;
    }


    private String getUrl(String query) {
        query = query.replaceAll(" ", "+");
        if (query.indexOf(",") < 0) {
            query = query.replaceFirst("\\+", ",+");
        }
        return query;
    }

    public boolean enteringTheRadius(String lat1, String lon1, String lat2, String lon2) {

        double latt1 = gradInRad(Double.valueOf(lat1));
        double latt2 = gradInRad(Double.valueOf(lat2));
        double lonn1 = gradInRad(Double.valueOf(lon1));
        double lonn2 = gradInRad(Double.valueOf(lon2));

        double cosLat1 = Math.cos(latt1);
        double cosLat2 = Math.cos(latt2);

        double sinLat1 = Math.sin(latt1);
        double sinLat2 = Math.sin(latt2);

        double deltaLon = lonn2 - lonn1;
        double cosDelta = Math.cos(deltaLon);
        double sinDelta = Math.sin(deltaLon);

        double x1 = Math.sqrt(Math.pow(cosLat2 * sinDelta, 2) +
                Math.pow(cosLat1 * sinLat2 - sinLat1 * cosLat2 * cosDelta, 2));

        double y1 = sinLat1 * sinLat2 + cosLat1 * cosLat2 * cosDelta;

        double arcTan = Math.atan2(x1, y1);
        double dist = arcTan * EARTH_RADIUS;


        return !(dist > 1100);
    }

    private double gradInRad(double i) {
        return i * Math.PI / 180;
    }


}
