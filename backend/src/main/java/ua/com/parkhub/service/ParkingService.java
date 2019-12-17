package ua.com.parkhub.service;

import org.hibernate.Session;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import ua.com.parkhub.dto.ParkingDTO;
import ua.com.parkhub.persistence.entities.Slot;
import ua.com.parkhub.persistence.impl.ParkingDAO;

import java.util.List;
import ua.com.parkhub.persistence.entities.Parking;

@Service
public class ParkingService {

    ParkingDAO parkingDAO;
    Parking parking;


    @Autowired
    public ParkingService(){
        this.parkingDAO = new ParkingDAO();
        this.parking = new Parking();
    }

    private static ModelMapper modelMapper = new ModelMapper();

    public List<ParkingDTO> findAllParking(){
        List<Parking> allParking = parkingDAO.findAll();
        List<ParkingDTO> parkingDTOList;
        parkingDTOList =  modelMapper.map(allParking, new TypeToken<List<ParkingDTO>>() {
        }.getType());
        int i=1;
        while (i <= parkingDTOList.size()){
            parkingDTOList.get(i).setAddress(findAddress(allParking.get(i)));
            parkingDTOList.get(i).setFullness(findFullness(allParking.get(i)));
            i++;
        }

        return parkingDTOList;
    }

    public ParkingDTO findParkingById(long id) {
        Parking parking = parkingDAO.findElementById(id);
        ParkingDTO parkingDTO =  modelMapper.map(parking, new TypeToken<ParkingDTO>() {
        }.getType());
        parkingDTO.setAddress(findAddress(parking));
        parkingDTO.setFullness(findFullness(parking));
        return parkingDTO;
    }

    protected String findAddress(Parking parking) {
        this.parking = parking;
        String address;
        address = parking.getAddress().getCity()
                + parking.getAddress().getStreet()
                + parking.getAddress().getBuilding();
        return address;
    }
    protected String findFullness(Parking parking){
        String fullness;
        int busySlots=0;

        for (Slot slot: parking.getSlots()){
            if (slot.isReserved()) busySlots++;

        }
        fullness = busySlots + "/" + parking.getSlotsNumber();
        return fullness;
    }
}
