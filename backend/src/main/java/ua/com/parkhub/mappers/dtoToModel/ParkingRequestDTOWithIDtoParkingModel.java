package ua.com.parkhub.mappers.dtoToModel;

import org.springframework.stereotype.Component;
import ua.com.parkhub.dto.ParkingRequestDTO;
import ua.com.parkhub.mappers.Mapper;
import ua.com.parkhub.model.AddressModel;
import ua.com.parkhub.model.ParkingInfoModel;
import ua.com.parkhub.model.ParkingModel;

@Component
public class ParkingRequestDTOWithIDtoParkingModel implements Mapper<ParkingRequestDTO, ParkingModel> {

    @Override
    public ParkingModel transform(ParkingRequestDTO from) {
        if(from == null) {
            return null;
        }
        ParkingModel parkingModel = new ParkingModel();
        ParkingInfoModel parkingInfoModel = new ParkingInfoModel();
        parkingInfoModel.setId(from.getId());
        parkingInfoModel.setParkingName(from.getParkingName());
        parkingInfoModel.setSlotsNumber(from.getSlotsNumber());
        parkingInfoModel.setTariff(from.getTariff());
        parkingInfoModel.setActive(true);
        AddressModel addressModel = new AddressModel();
        addressModel.setCity(from.getCity());
        addressModel.setStreet(from.getStreet());
        addressModel.setBuilding(from.getBuilding());
        parkingInfoModel.setAddressModel(addressModel);
        parkingModel.setInfo(parkingInfoModel);
        return parkingModel;
    }
}