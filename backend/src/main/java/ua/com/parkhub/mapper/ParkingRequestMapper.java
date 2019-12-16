package ua.com.parkhub.mapper;

import org.mapstruct.Mapper;
import ua.com.parkhub.dto.ParkingDTO;
import ua.com.parkhub.dto.ParkingRequestDTO;
import ua.com.parkhub.model.ParkingModel;
import ua.com.parkhub.model.ParkingRequestModel;

@Mapper(componentModel = "spring")
public interface ParkingRequestMapper {
    ParkingRequestDTO parkingRequestModelToParkingRequestDTO(ParkingRequestModel parkingRequestModel);
    ParkingRequestModel parkingRequestDTOToParkingRequestModel(ParkingRequestDTO parkingRequestDTO);
}
