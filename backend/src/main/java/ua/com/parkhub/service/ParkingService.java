package ua.com.parkhub.service;

import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.com.parkhub.dto.ParkingDTO;
import ua.com.parkhub.mapper.ParkingRequestMapper;
import ua.com.parkhub.mapper.ParkingMapper;
import ua.com.parkhub.model.ParkingModel;
import ua.com.parkhub.model.ParkingModelDTO;
import ua.com.parkhub.persistence.entities.Address;
import ua.com.parkhub.persistence.entities.Parking;
import ua.com.parkhub.persistence.entities.Slot;
import ua.com.parkhub.persistence.impl.AddressDAO;
import ua.com.parkhub.persistence.impl.ParkingDAO;
import ua.com.parkhub.persistence.impl.UserDAO;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ParkingService {

    private ParkingDAO parkingDAO;
    private final AddressDAO addressDAO;
    private ParkingMapper parkingMapper;
    private final UserDAO userDAO;
    private final ParkingRequestMapper parkingRequestMapper;

    @Autowired
    public ParkingService(ParkingDAO parkingDAO, AddressDAO addressDAO, UserDAO userDAO, ParkingMapper parkingMapper){
        this.parkingDAO = parkingDAO;
        this.addressDAO = addressDAO;
        this.parkingMapper = parkingMapper;
        this.userDAO = userDAO;
        parkingRequestMapper = Mappers.getMapper( ParkingRequestMapper.class);
    }

    public List<ParkingDTO> findAllParking(){
        List<Parking> allParking = parkingDAO.findAll();
        List<ParkingDTO> parkingDTOList = new ArrayList<>();
        for (Parking parkingDAO:allParking){
            ParkingDTO parkingDTO = new ParkingModelDTO().transform(parkingDAO);
            parkingDTO.setAddress(findAddress(parkingDAO));
            parkingDTO.setFullness(findFullness(parkingDAO));
            parkingDTOList.add(parkingDTO);
        }
        return parkingDTOList;
    }

    public boolean isParkingNameUnique(ParkingModel parkingRequestModel) {
        Long count = parkingDAO.
                countOfParkingsByName(parkingRequestModel.getParkingName());
        return count == 0;
    }

    public boolean checkIfAddressIsUnique(ParkingModel parkingRequestModel) {
        Long count = parkingDAO.
                countOfParkingsByAddress(parkingRequestModel.getAddressModel());
        return count == 0;
    }

    @Transactional
    public void createParkingByOwnerID(ParkingModel parkingModel, long id) {
        Address address = parkingRequestMapper.parkingModelToAddress(parkingModel);
        Parking parking = parkingRequestMapper.parkingModelToParking(parkingModel);
        parking.setOwner(userDAO.findElementByIdSimple(id));
        parking.setAddress(address);
        addressDAO.addElement(address);
        parkingDAO.addElement(parking);
    }

    public List<ParkingModel> findAll(){
        return parkingDAO.findAll().stream().map(parkingMapper::fromEntityToModel).collect(Collectors.toList());
    }

    public ParkingModel findParkingByIdYaroslav(long id){

        return parkingMapper.fromEntityToModel(parkingDAO.findElementByIdSimple(id));
    }

    public ParkingDTO findParkingById(long id) {
        Parking parking = parkingDAO.findElementByIdSimple(id);
        ParkingDTO parkingDTO = new ParkingModelDTO().transform(parking);
        parkingDTO.setAddress(findAddress(parking));
        parkingDTO.setFullness(findFullness(parking));
        return parkingDTO;
    }

    protected String findAddress(Parking parking) {
        String address="";
        address =
                parking.getAddress().getCity()+ " " +
                parking.getAddress().getStreet() + " "
                + parking.getAddress().getBuilding();
        return address;
    }
    protected String findFullness(Parking parking){
        String fullness;
        int busySlots;
        busySlots = (int) parking.getSlots().stream().filter(Slot::isReserved).count();
        fullness = busySlots + "/" + parking.getSlotsNumber();
        return fullness;
    }
}
