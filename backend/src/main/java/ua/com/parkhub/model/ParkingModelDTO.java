package ua.com.parkhub.model;

import ua.com.parkhub.dto.ParkingDTO;
import ua.com.parkhub.persistence.entities.Parking;

public class ParkingModelDTO implements Mapper<Parking, ParkingDTO> {

    @Override
    public ParkingDTO transform(Parking from) {
        ParkingDTO parkingDTO = new ParkingDTO();
         parkingDTO.setId(from.getId());
         parkingDTO.setParkingName(from.getParkingName());
         parkingDTO.setSlotNumber(from.getSlotsNumber());
         parkingDTO.setTariff(from.getTariff());
        return parkingDTO;
    }
}
