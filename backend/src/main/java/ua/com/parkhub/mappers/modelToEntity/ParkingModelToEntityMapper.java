package ua.com.parkhub.mappers.modelToEntity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.com.parkhub.mappers.Mapper;
import ua.com.parkhub.model.ParkingModel;
import ua.com.parkhub.persistence.entities.Address;
import ua.com.parkhub.persistence.entities.Parking;
import ua.com.parkhub.persistence.entities.Slot;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ParkingModelToEntityMapper implements Mapper<ParkingModel, Parking> {

    private SlotModelToEntityMapper slotsMapper;

    @Autowired
    public ParkingModelToEntityMapper(SlotModelToEntityMapper slotsMapper) {
        this.slotsMapper = slotsMapper;
    }

    @Override
    public Parking transform(ParkingModel from) {
        Parking parking = new Parking();
        Address address = new Address();
        address.setCity(from.getInfo().getAddressModel().getCity());
        address.setStreet(from.getInfo().getAddressModel().getStreet());
        address.setBuilding(from.getInfo().getAddressModel().getBuilding());
        parking.setParkingName(from.getInfo().getParkingName());
        parking.setAddress(address);
        parking.setTariff(from.getInfo().getTariff());
        parking.setSlotsNumber(from.getInfo().getSlotsNumber());
        List<Slot> slots = from.getSlots().stream().map(slotsMapper::transform).collect(Collectors.toList());
        slots.forEach(slot -> slot.setParking(parking));
        parking.setSlots(slots);
        return parking;
    }
}
