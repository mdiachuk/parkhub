package ua.com.parkhub.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.com.parkhub.service.impl.AddressGeoService;

@RestController
@RequestMapping("/search")
public class SearchController {

    private final AddressGeoService addressGeoService;

    @Autowired
    public SearchController(AddressGeoService addressGeoService) {
        this.addressGeoService = addressGeoService;
    }

    @PostMapping
    public ResponseEntity searchParking(@RequestBody String query) {

        addressGeoService.getLatLon(query);

        return null;
    }

}
