package ua.com.parkhub.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.com.parkhub.exceptions.ParkingDoesntExistException;
import ua.com.parkhub.mappers.fromEntityToModel.ParkingEntityToModelMapper;
import ua.com.parkhub.mappers.fromModelToEntity.AddressModelToEntityMapper;
import ua.com.parkhub.mappers.fromModelToEntity.ParkingModelToEntityMapper;
import ua.com.parkhub.model.AddressModel;
import ua.com.parkhub.model.ParkingModel;
import ua.com.parkhub.persistence.entities.Address;
import ua.com.parkhub.persistence.entities.Parking;
import ua.com.parkhub.persistence.impl.AddressDAO;
import ua.com.parkhub.persistence.impl.ParkingDAO;

import java.lang.reflect.Field;
import java.util.*;
import java.util.stream.Collectors;


@Service
@Transactional
public class ParkingService {

    private ParkingDAO parkingDAO;
    private AddressDAO addressDAO;

    @Autowired
    public ParkingService(ParkingDAO parkingDAO, AddressDAO addressDAO) {
        this.parkingDAO = parkingDAO;
        this.addressDAO = addressDAO;
    }

    public List<ParkingModel> findAllParking(){

        return parkingDAO.findAll();
    }

    public ParkingModel findParkingById(long id){

        return parkingDAO.findElementById(id).orElseThrow(() -> new ParkingDoesntExistException("Such parking doesn't exist"));
    }

    public void updateParking(Long id, ParkingModel parkingModelParam) throws NoSuchFieldException, IllegalAccessException {
        ParkingModel parkingModel = findParkingById(id);
        Field[] paramFields = parkingModelParam.getClass().getDeclaredFields();
        List<String> fieldsNameList = Arrays.stream(paramFields).filter(field -> {
            try {
                return field.get(parkingModel) != null;
            } catch (IllegalAccessException ex) {
                return false;
            }
        }).map(Field::getName).collect(Collectors.toList());

        /*for (String name : fieldsNameList) {
            Field fieldParam = parkingModelParam.getClass().getDeclaredField(name);
            Field field = parkingModelParam.getClass().getDeclaredField(name);
            field.setAccessible(true);
            fieldParam.setAccessible(true);
            field.set(parkingModel, fieldParam.get(parkingModelParam));
        }*/



        if (fieldsNameList.contains("addressModel")){
            AddressModel a = addressDAO.addWithResponse(parkingModelParam.getAddressModel()); //from non id model gets model with id
            parkingModel.setAddressModel(a);
        }

        parkingDAO.updateElement(parkingModel);
    }
}
