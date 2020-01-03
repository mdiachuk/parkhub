package ua.com.parkhub.service.impl;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ua.com.parkhub.dto.AddressGeoDTO;

@Service
public class AddressGeoService {

    private RestTemplate restTemplate;


    public String getLatLon(String query) {
        restTemplate = new RestTemplate();
        String URL = "https://nominatim.openstreetmap.org/search.php?q="+
                getUrl(query)+"&format=json";
        ResponseEntity<AddressGeoDTO[]> adressGeoDTO =
                restTemplate.getForEntity (URL, AddressGeoDTO[].class);
        System.out.println(adressGeoDTO.getBody()[0].toString());
        return adressGeoDTO.toString();
    }


    private String getUrl(String query){
        query = query.replaceAll(" ","+");
        if (query.indexOf(",")<0){
            query = query.replaceFirst("\\+","+,");
        }
        return query;
    }

}
