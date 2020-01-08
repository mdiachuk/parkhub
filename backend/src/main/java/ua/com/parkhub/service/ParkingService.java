package ua.com.parkhub.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.com.parkhub.exceptions.ParkingDoesntExistException;
import ua.com.parkhub.exceptions.StatusCode;
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

    public List<ParkingModel> findAllParking() {

        return parkingDAO.findAll();
    }

    public ParkingModel findParkingById(long id) {

        return parkingDAO.findElementById(id).orElseThrow(() -> new ParkingDoesntExistException(StatusCode.PARKING_DOESNT_EXIST));
    }

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
                else
                return value != null;
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

        /*fieldsNameList.stream().peek(name -> {
            try {
                Field fieldParam = parkingModelParam.getClass().getDeclaredField(name);
                Field field = parkingModel.getClass().getDeclaredField(name);
                field.setAccessible(true);
                fieldParam.setAccessible(true);
                field.set(parkingModel, fieldParam.get(parkingModelParam));
            } catch (NoSuchFieldException | IllegalAccessException e) {
                e.printStackTrace();
            }
        });*/


            if (fieldsNameList.contains("addressModel")) {
                AddressModel a = addressDAO.addWithResponse(parkingModelParam.getAddressModel()); //from non id model gets model with id
                parkingModel.setAddressModel(a);
            }

            parkingDAO.updateElement(parkingModel);
        }
    }
}
