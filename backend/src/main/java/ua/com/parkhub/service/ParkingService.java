package ua.com.parkhub.service;

import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.com.parkhub.mapper.AddressMapper;
import ua.com.parkhub.mapper.ParkingMapper;
import ua.com.parkhub.mapper.UserMapper;
import ua.com.parkhub.model.AddressModel;
import ua.com.parkhub.model.ParkingModel;
import ua.com.parkhub.model.ParkingRequestModel;
import ua.com.parkhub.model.UserModel;
import ua.com.parkhub.persistence.entities.*;
import ua.com.parkhub.persistence.impl.*;

@Service
@Transactional
public class ParkingService {

    private final ParkingDAO parkingDAO;
    private final SlotDAO slotDAO;
    private final AddressDAO addressDAO;
    private final UserDAO userDAO;
    private final UserRoleDAO userRoleDAO;
    private final CustomerDAO customerDAO;
    private final AddressMapper addressMapper;
    private final ParkingMapper parkingMapper;
  //  private final UserMapper userMapper;

    @Autowired
    public ParkingService(ParkingDAO parkingDAO, SlotDAO slotDAO,AddressDAO addressDAO,UserDAO userDAO,UserRoleDAO userRoleDAO,CustomerDAO customerDAO) {
        this.parkingDAO = parkingDAO;
        this.slotDAO = slotDAO;
        this.addressDAO = addressDAO;
        this.userDAO = userDAO;
        this.userRoleDAO = userRoleDAO;
        this.customerDAO = customerDAO;
        addressMapper = Mappers.getMapper( AddressMapper.class);
        parkingMapper = Mappers.getMapper( ParkingMapper.class);
    }
    public void saveUser(User theUser) {

        userDAO.addElement(theUser);
    }
    public void saveUserRole(UserRole theUserRole) {

        userRoleDAO.addElement(theUserRole);
    }

    public void saveCustomer(Customer theCustomer) {

        customerDAO.addElement(theCustomer);
    }

    public void saveAdress(Address theAddress) {

        addressDAO.addElement(theAddress);
    }

//    @Transactional
//    public void createParking(Address address, Parking parking) {
//        parking.setOwner(userDAO.findElementByEmail("hnjf"));
//        arking parking1 = parkingMapper.parkingModelToParking(parking);
//        parking1.setOwner(userDAO.findElementById(1));
//        parking.setAddress(address);
//        Address address1 = addressMapper.addressModelToAddress(address);
//        parkingMapper.parkingModelToParking(parking).
//                setAddress(addressMapper.addressModelToAddress(address));
//        parking1.setAddress(address1);
//        addressDAO.addElement(address);
//        parkingDAO.addElement(parking);
        //addressDAO.addElement(addressMapper.addressModelToAddress(address));
        //parkingDAO.addElement(parkingMapper.parkingModelToParking(parking));

//    }
    @Transactional
    public void createParkingFROMModels(AddressModel address, ParkingModel parking) {
        //parking.setOwner(userDAO.findElementById(9));
        Parking parking1 = parkingMapper.parkingModelToParking(parking);
        Address address1 = addressMapper.addressModelToAddress(address);
        parking1.setOwner(userDAO.findElementByEmail(parking.getOwnerEmail()));
        parking1.setAddress(address1);
//        parkingMapper.parkingModelToParking(parking).
//                setAddress(addressMapper.addressModelToAddress(address));
        //parking1.setAddress(address1);
        addressDAO.addElement(address1);
        parkingDAO.addElement(parking1);
        //addressDAO.addElement(addressMapper.addressModelToAddress(address));
        //parkingDAO.addElement(parkingMapper.parkingModelToParking(parking));

    }

    @Transactional
    public void createParkingFROMParkingRequestModel(ParkingRequestModel parkingRequestModel) {
        //parking.setOwner(userDAO.findElementById(9));
        ParkingModel parkingModel = new ParkingModel();
        parkingModel.setParkingName(parkingRequestModel.getParkingName());
        parkingModel.setSlotsNumber(parkingRequestModel.getSlotsNumber());
        parkingModel.setTariff(parkingRequestModel.getTariff());
        AddressModel addressModel = new AddressModel();
        addressModel.setBuilding(parkingRequestModel.getBuilding());
        addressModel.setCity(parkingRequestModel.getCity());
        addressModel.setStreet(parkingRequestModel.getStreet());
        parkingModel.setAddress(addressModel);//to think about ownerId
        Address address = addressMapper.addressModelToAddress(addressModel);
        Parking parking = parkingMapper.parkingModelToParking(parkingModel);
        parking.setOwner(userDAO.findElementByEmail(parkingRequestModel.getOwnerEmail()));
        parking.setAddress(address);
//        parkingMapper.parkingModelToParking(parking).
//                setAddress(addressMapper.addressModelToAddress(address));
//        parking1.setAddress(address1);
//        addressDAO.addElement(address1);
//        parkingDAO.addElement(parking1);
        addressDAO.addElement(address);
        parkingDAO.addElement(parking);

    }


}
