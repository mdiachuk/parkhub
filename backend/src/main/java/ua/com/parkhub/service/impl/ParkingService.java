package ua.com.parkhub.service.impl;

import org.mapstruct.factory.Mappers;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.com.parkhub.mappers.ParkingRequestMapper;
import ua.com.parkhub.model.ParkingModel;
import ua.com.parkhub.persistence.entities.Address;
import ua.com.parkhub.persistence.entities.Parking;
import ua.com.parkhub.persistence.impl.AddressDAO;
import ua.com.parkhub.persistence.impl.ParkingDAO;
import ua.com.parkhub.persistence.impl.UserDAO;

import java.util.List;
import java.util.Optional;

//import ua.com.parkhub.service.IParkingService;

@Service
public class ParkingService
//        implements IParkingService
{


    private ParkingDAO parkingDAO;
    private final AddressDAO addressDAO;
    private final UserDAO userDAO;
    private final ParkingRequestMapper parkingRequestMapper;


    private final ModelMapper mapper;

    @Autowired
    public ParkingService(ParkingDAO parkingDAO, AddressDAO addressDAO,
                          UserDAO userDAO,
                          ModelMapper mapper
    ){
        this.parkingDAO = parkingDAO;
        this.addressDAO = addressDAO;
        this.userDAO = userDAO;
        this.mapper = mapper;
        parkingRequestMapper = Mappers.getMapper( ParkingRequestMapper.class);
    }

//    @Transactional(readOnly = true)
//    public ParkingModel findParkingByIdWithSlotList(long id) {
//        ParkingModel parking = parkingDAO.findElementById(id)
//                .orElseThrow(() -> new ParkHubException("No Parking found with id " + id));
//        Hibernate.initialize(parking.getSlots());
//        if (parking.isActive()) {
//
//            return mapper.map(parking, ParkingModel.class);
//        }
//        throw new ParkHubException("Unfortunately this parking is temporary unavailable");
//    }

//    @Transactional(readOnly = true)
//    public List<ParkingModel> findAllParking() {
//        List<ParkingModel> parkingList = parkingDAO.findAll();
//        if (parkingList.isEmpty()) {
//            throw new ParkHubException("Unfortunately all parkings are temporary unavailable");
//        }
//       return parkingList.stream()
//                .map(parkingEntity -> mapper.map(parkingEntity, ParkingModel.class))
//               .collect(Collectors.toList());
//    }


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
//        if (userDAO.findElementById(id).isPresent()) {
//            parking.setOwner(userDAO.findElementById(id).get());}
//        parking.setAddress(address);
//        addressDAO.addElement(address);
//        parkingDAO.addElement(parking);
    }

    public List<ParkingModel> findAllParkingModel(){

        return parkingDAO.findAll();
    }

    public Optional<ParkingModel> findParkingById(long id){

        return  parkingDAO.findElementById(id);
    }

}
