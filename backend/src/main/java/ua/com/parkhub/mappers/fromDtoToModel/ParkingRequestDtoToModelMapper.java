package ua.com.parkhub.mappers.fromDtoToModel;

import ua.com.parkhub.dto.ParkingRequestDTO;
import ua.com.parkhub.mappers.Mapper;
import ua.com.parkhub.model.ParkingModel;

public class ParkingRequestDtoToModelMapper implements Mapper<ParkingRequestDTO, ParkingModel> {
    @Override
    public ParkingModel transform(ParkingRequestDTO from) {
        ParkingModel parkingModel = new ParkingModel();
        parkingModel.setParkingName(from.getParkingName());
        parkingModel.setSlotsNumber(from.getSlotsNumber());
        parkingModel.setTariff(from.getTariff());
        parkingModel.set
    }
}
