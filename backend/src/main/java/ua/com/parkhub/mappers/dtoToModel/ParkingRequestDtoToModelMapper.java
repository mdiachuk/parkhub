package ua.com.parkhub.mappers.dtoToModel;

import org.springframework.stereotype.Component;
import ua.com.parkhub.dto.ParkingUpdateRequestDTO;
import ua.com.parkhub.mappers.Mapper;
import ua.com.parkhub.model.AddressModel;
import ua.com.parkhub.model.ParkingInfoModel;
import ua.com.parkhub.model.ParkingModel;

@Component
public class ParkingRequestDtoToModelMapper implements Mapper<ParkingUpdateRequestDTO, ParkingModel> {

    @Override
    public ParkingModel transform(ParkingUpdateRequestDTO from) {
        ParkingModel parkingModel = new ParkingModel();
        ParkingInfoModel parkingInfoModel = new ParkingInfoModel();
        parkingInfoModel.setParkingName(from.getParkingName());
        parkingInfoModel.setSlotsNumber(from.getSlotsNumber());
        parkingInfoModel.setTariff(from.getTariff());
        if (from.getCity() != null || from.getStreet() != null || from.getBuilding() != null) {
            AddressModel addressModel = new AddressModel();
            addressModel.setCity(from.getCity());
            addressModel.setBuilding(from.getBuilding());
            addressModel.setStreet(from.getStreet());
            parkingInfoModel.setAddressModel(addressModel);
        }
        parkingModel.setInfo(parkingInfoModel);
        return parkingModel;
    }
}
