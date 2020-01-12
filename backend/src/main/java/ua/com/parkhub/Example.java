package ua.com.parkhub;

import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import ua.com.parkhub.dto.AddressGeoDTO;

import java.io.UnsupportedEncodingException;

public class Example {




    public void getPAram() throws UnsupportedEncodingException {
        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<AddressGeoDTO[]>
          AdressGeoDTO =
                restTemplate.getForEntity ("https://nominatim.openstreetmap.org/search.php?q=київ,+леся+курбаса+5а&format=json",
                                AddressGeoDTO[].class);

        System.out.println(AdressGeoDTO.getBody()[0].getLat());
        System.out.println(AdressGeoDTO.getBody()[0].getLon());



    }

}
