package ua.com.parkhub.service.impl;

import org.hibernate.Hibernate;
import org.mapstruct.factory.Mappers;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.com.parkhub.dto.ParkingDTO;
import ua.com.parkhub.exceptions.ParkHubException;
import ua.com.parkhub.mapper.ParkingMapper;
import ua.com.parkhub.mapper.ParkingRequestMapper;
import ua.com.parkhub.model.ParkingModel;
import ua.com.parkhub.model.ParkingModelDTO;
import ua.com.parkhub.persistence.entities.Address;
import ua.com.parkhub.persistence.entities.Parking;
import ua.com.parkhub.persistence.entities.Slot;
import ua.com.parkhub.persistence.impl.AddressDAO;
import ua.com.parkhub.persistence.impl.ParkingDAO;
import ua.com.parkhub.persistence.impl.UserDAO;
import ua.com.parkhub.service.IParkingService;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ParkingService implements IParkingService {


    private ParkingDAO parkingDAO;
    private final AddressDAO addressDAO;
    private ParkingMapper parkingMapper;
    private final UserDAO userDAO;
    private final ParkingRequestMapper parkingRequestMapper;


    private final ModelMapper mapper;

    @Autowired
    public ParkingService(ParkingDAO parkingDAO, AddressDAO addressDAO,
                          UserDAO userDAO, ParkingMapper parkingMapper,
                          ModelMapper mapper
    ){
        this.parkingDAO = parkingDAO;
        this.addressDAO = addressDAO;
        this.parkingMapper = parkingMapper;
        this.userDAO = userDAO;
        this.mapper = mapper;
        parkingRequestMapper = Mappers.getMapper( ParkingRequestMapper.class);
    }

    @Transactional(readOnly = true)
    public ua.com.parkhub.model.Parking findParkingByIdWithSlotList(long id) {
        Parking parking = parkingDAO.findElementById(id)
                .orElseThrow(() -> new ParkHubException("No Parking found with id " + id));
        Hibernate.initialize(parking.getSlots());
        if (parking.isActive()) {

            return mapper.map(parking, ua.com.parkhub.model.Parking.class);
        }
        throw new ParkHubException("Unfortunately this parking is temporary unavailable");
    }

    @Transactional(readOnly = true)
    public List<ua.com.parkhub.model.Parking> findAllParking() {
        List<Parking> parkingList = parkingDAO.findAll();
        if (parkingList.isEmpty()) {
            throw new ParkHubException("Unfortunately all parkings are temporary unavailable");
        }
        return parkingList.stream()
                .map(parkingEntity -> mapper.map(parkingEntity, ua.com.parkhub.model.Parking.class))
                .collect(Collectors.toList());
    }

    public List<ParkingDTO> findAllParkings(){
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
    /**
     * New
     */

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

    @javax.transaction.Transactional
    public void createParkingByOwnerID(ParkingModel parkingModel, long id) {
        Address address = parkingRequestMapper.parkingModelToAddress(parkingModel);
        Parking parking = parkingRequestMapper.parkingModelToParking(parkingModel);
        if (userDAO.findElementById(id).isPresent()) {
            parking.setOwner(userDAO.findElementById(id).get());}
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