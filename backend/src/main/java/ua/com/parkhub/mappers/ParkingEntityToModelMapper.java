package ua.com.parkhub.mappers;

import org.springframework.stereotype.Component;
import ua.com.parkhub.model.ParkingModel;
import ua.com.parkhub.persistence.entities.Parking;

import java.util.stream.Collectors;

@Component
public class ParkingEntityToModelMapper implements Mapper<Parking, ParkingModel> {

    @Override
    public ParkingModel transform(Parking from) {
        ParkingModel parkingModel = new ParkingModel();
        SlotEntityToModelMapper slotEntityToModelMapper = new SlotEntityToModelMapper();
        parkingModel.setId(from.getId());
        parkingModel.setParkingName(from.getParkingName());
        parkingModel.setAddressModel(new AddressEntityToModelMapper().transform(from.getAddress()));
        parkingModel.setSlotsNumber(from.getSlotsNumber());
        parkingModel.setTariff(from.getTariff());
        parkingModel.setActive(from.isActive());
        parkingModel.setSlots(from.getSlots().stream().map(slotEntityToModelMapper::transform).collect(Collectors.toSet()));
        return parkingModel;
    }
}
