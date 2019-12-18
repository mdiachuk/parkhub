package ua.com.parkhub.service;

import org.hibernate.Session;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import ua.com.parkhub.dto.ParkingDTO;
import ua.com.parkhub.model.ParkingModelDTO;
import ua.com.parkhub.persistence.entities.Slot;
import ua.com.parkhub.persistence.impl.ParkingDAO;

import java.util.ArrayList;
import java.util.List;
import ua.com.parkhub.persistence.entities.Parking;

@Service
public class ParkingService {

    ParkingDAO parkingDAO;

    @Autowired
    public ParkingService(ParkingDAO parkingDAO){
        this.parkingDAO = parkingDAO;
    }

    public List<ParkingDTO> findAllParking(){
        List<Parking> allParking = parkingDAO.findAll();
        List<ParkingDTO> parkingDTOList = new ArrayList<>();
        for (Parking parkingDAO:allParking){
            ParkingDTO parkingDTO = new ParkingModelDTO().transform(parkingDAO);
            parkingDTO.setAddress(findAddress(parkingDAO));
            parkingDTO.setFullness(findFullness(parkingDAO));
            parkingDTOList.add(parkingDTO);
        }
        return parkingDTOList;
    }

    public ParkingDTO findParkingById(long id) {
        Parking parking = parkingDAO.findElementById(id);
        ParkingDTO parkingDTO = new ParkingModelDTO().transform(parking);
        parkingDTO.setAddress(findAddress(parking));
        parkingDTO.setFullness(findFullness(parking));
        return parkingDTO;
    }

    protected String findAddress(Parking parking) {
        String address="";
        address =
                parking.getAddress().getCity()+ " " +
                parking.getAddress().getStreet() + " "
                + parking.getAddress().getBuilding();
        return address;
    }
    protected String findFullness(Parking parking){
        String fullness;
        int busySlots=0;
        busySlots = (int) parking.getSlots().stream().filter(Slot::isReserved).count();

/*        for (Slot slot: parking.getSlots()){
            if (slot.isReserved()) busySlots++;

        }*/
        fullness = busySlots + "/" + parking.getSlotsNumber();
        return fullness;
    }
}
