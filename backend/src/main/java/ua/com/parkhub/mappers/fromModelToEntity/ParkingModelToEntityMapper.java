package ua.com.parkhub.mappers.fromModelToEntity;

import org.springframework.stereotype.Component;
import ua.com.parkhub.mappers.Mapper;
import ua.com.parkhub.model.ParkingModel;
import ua.com.parkhub.persistence.entities.Address;
import ua.com.parkhub.persistence.entities.Parking;

@Component
public class ParkingModelToEntityMapper implements Mapper<ParkingModel, Parking> {
    @Override
    public Parking transform(ParkingModel from) {
        Parking parking = new Parking();
        Address address = new Address();
        address.setCity(from.getAddressModel().getCity());
        address.setStreet(from.getAddressModel().getStreet());
        address.setBuilding(from.getAddressModel().getBuilding());
        parking.setParkingName(from.getParkingName());
        parking.setAddress(address);
        parking.setTariff(from.getTariff());
        parking.setSlotsNumber(from.getSlotsNumber());
        return parking;
    }
}
