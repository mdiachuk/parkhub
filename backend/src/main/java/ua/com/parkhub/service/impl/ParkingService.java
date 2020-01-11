package ua.com.parkhub.service.impl;

import org.mapstruct.factory.Mappers;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.com.parkhub.exceptions.ParkingDoesntExistException;
import ua.com.parkhub.exceptions.StatusCode;
import ua.com.parkhub.model.AddressModel;
import ua.com.parkhub.model.ParkingModel;
import ua.com.parkhub.persistence.entities.Address;
import ua.com.parkhub.persistence.entities.Parking;
import ua.com.parkhub.persistence.impl.AddressDAO;
import ua.com.parkhub.persistence.impl.ParkingDAO;
import ua.com.parkhub.persistence.impl.UserDAO;
import ua.com.parkhub.service.IParkingService;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ParkingService  {


    private ParkingDAO parkingDAO;
    private final AddressDAO addressDAO;
    private final UserDAO userDAO;


    @Autowired
    public ParkingService(ParkingDAO parkingDAO, AddressDAO addressDAO,
                          UserDAO userDAO
    ){
        this.parkingDAO = parkingDAO;
        this.addressDAO = addressDAO;
        this.userDAO = userDAO;
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
        AddressModel address = parkingModel.getAddressModel();
        if (userDAO.findElementById(id).isPresent()) {
            parkingModel.setOwner(userDAO.findElementById(id).get());}
        AddressModel addressModel = addressDAO.addWithResponse(address);
        parkingModel.setAddressModel(addressModel);
        parkingDAO.addElement(parkingModel);
    }

    public List<ParkingModel> findAllParkingModel(){

        return parkingDAO.findAll();
    }

    public List<ParkingModel> findAllParking() {

        return parkingDAO.findAll();
    }

    public ParkingModel findParkingById(long id) {

        return parkingDAO.findElementById(id).orElseThrow(() -> new ParkingDoesntExistException(StatusCode.PARKING_DOESNT_EXIST));
    }


    @Transactional
    public void updateParking(Long id, ParkingModel parkingModelParam) {
        ParkingModel parkingModel = findParkingById(id);
        Field[] paramFields = parkingModelParam.getClass().getDeclaredFields();
        List<String> fieldsNameList = Arrays.stream(paramFields).filter(field -> {
            try {
                field.setAccessible(true);
                Object value = field.get(parkingModelParam);
                if (value instanceof Integer){
                    return (Integer) value != 0;
                }
                else {
                    return value != null;
                }
            } catch (IllegalAccessException ex) {
                return false;
            }
        }).map(Field::getName).collect(Collectors.toList());

        for (String name : fieldsNameList) {
            try {
                Field fieldParam = parkingModelParam.getClass().getDeclaredField(name);
                Field field = parkingModel.getClass().getDeclaredField(name);
                field.setAccessible(true);
                fieldParam.setAccessible(true);
                field.set(parkingModel, fieldParam.get(parkingModelParam));
            } catch (NoSuchFieldException | IllegalAccessException e) {
                e.printStackTrace();
            }

            if (fieldsNameList.contains("addressModel")) {
                AddressModel a = addressDAO.addWithResponse(parkingModelParam.getAddressModel()); //from non id model gets model with id
                parkingModel.setAddressModel(a);
            }

            parkingDAO.updateElement(parkingModel);
        }
    }


}