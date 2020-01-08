package ua.com.parkhub.mappers.dtoToModel;

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
        parkingModel.getInfo().setParkingName(from.getParkingName());
        parkingModel.getInfo().setSlotsNumber(from.getSlotsNumber());
        parkingModel.getInfo().setTariff(from.getTariff());
        AddressModel addressModel = new AddressModel();
        addressModel.setCity(from.getCity());
        addressModel.setBuilding(from.getBuilding());
        addressModel.setStreet(from.getStreet());
        parkingModel.getInfo().setAddressModel(addressModel);
        return parkingModel;
    }
}

