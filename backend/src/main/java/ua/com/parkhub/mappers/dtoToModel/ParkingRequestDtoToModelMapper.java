package ua.com.parkhub.mappers.dtoToModel;

import org.springframework.stereotype.Component;
import ua.com.parkhub.dto.ParkingUpdateRequestDTO;
import ua.com.parkhub.mappers.Mapper;
import ua.com.parkhub.model.AddressModel;
import ua.com.parkhub.model.ParkingModel;

@Component
public class ParkingRequestDtoToModelMapper implements Mapper<ParkingUpdateRequestDTO, ParkingModel> {

    @Override
    public ParkingModel transform(ParkingUpdateRequestDTO from) {
        ParkingModel parkingModel = new ParkingModel();
        parkingModel.setParkingName(from.getParkingName());
        parkingModel.setSlotsNumber(from.getSlotsNumber());
        parkingModel.setTariff(from.getTariff());
        if (from.getCity() != null || from.getStreet() != null || from.getBuilding() != null) {
            AddressModel addressModel = new AddressModel();
            addressModel.setCity(from.getCity());
            addressModel.setBuilding(from.getBuilding());
            addressModel.setStreet(from.getStreet());
            parkingModel.setAddressModel(addressModel);
        }
        return parkingModel;
    }
}
