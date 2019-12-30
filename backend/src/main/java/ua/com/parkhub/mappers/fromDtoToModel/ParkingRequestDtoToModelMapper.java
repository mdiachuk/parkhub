package ua.com.parkhub.mappers.fromDtoToModel;

import org.springframework.stereotype.Component;
import ua.com.parkhub.dto.ParkingRequestDTO;
import ua.com.parkhub.mappers.Mapper;
import ua.com.parkhub.model.AddressModel;
import ua.com.parkhub.model.ParkingModel;

@Component
public class ParkingRequestDtoToModelMapper implements Mapper<ParkingRequestDTO, ParkingModel> {
    @Override
    public ParkingModel transform(ParkingRequestDTO from) {
        ParkingModel parkingModel = new ParkingModel();
        parkingModel.setParkingName(from.getParkingName());
        parkingModel.setSlotsNumber(from.getSlotsNumber());
        parkingModel.setTariff(from.getTariff());
        AddressModel addressModel = new AddressModel();
        addressModel.setCity(from.getCity());
        addressModel.setBuilding(from.getBuilding());
        addressModel.setStreet(from.getStreet());
        parkingModel.setAddressModel(addressModel);
        return parkingModel;
    }
}
