package ua.com.parkhub.service.impl;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.com.parkhub.exceptions.ParkHubException;
import ua.com.parkhub.mappers.entityToModel.ParkingEntityToModelMapper;
import ua.com.parkhub.model.BookingModel;
import ua.com.parkhub.model.ParkingModel;
import ua.com.parkhub.model.SlotModel;
import ua.com.parkhub.persistence.impl.ParkingDAO;
import ua.com.parkhub.service.IParkingService;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@Transactional
public class ParkingService implements IParkingService {

    private ParkingDAO parkingDAO;
    private BookingService bookingService;

    @Autowired
    public ParkingService(ParkingDAO parkingDAO, BookingService bookingService) {
        this.parkingDAO = parkingDAO;
        this.bookingService = bookingService;
    }

    public List<ParkingModel> findAllParking() {

        return parkingDAO.findAll();
    }

    public Optional<ParkingModel> findParkingById(long id) {

        return parkingDAO.findElementById(id);
    }

    public void updateParking(Long id, ParkingModel parkingModelParam) throws NoSuchFieldException, IllegalAccessException {
        ParkingModel parkingModel = findParkingById(id).get();
        Field[] paramFields = parkingModelParam.getClass().getDeclaredFields();
        List<String> fieldsNameList = Arrays.stream(paramFields).filter(Objects::nonNull).map(Field::getName).collect(Collectors.toList());
        for (String name : fieldsNameList) {
            Field fieldParam = parkingModelParam.getClass().getDeclaredField(name);
            Field field = parkingModelParam.getClass().getDeclaredField(name);
            field.setAccessible(true);
            fieldParam.setAccessible(true);
            field.set(parkingModel, fieldParam.get(parkingModelParam));
        }
        parkingDAO.updateElement(parkingModel);
    }

    @Transactional(readOnly = true)
    public ParkingModel findParkingByIdWithSlotList(long id) {
        ParkingModel parking = parkingDAO.findElementById(id)
                .orElseThrow(() -> new ParkHubException("No Parking found with id " + id));
        Hibernate.initialize(parking.getSlots());
        if (parking.getInfo().isActive()) {
            return parking;
        }
        throw new ParkHubException("Unfortunately this parking is temporary unavailable");
    }

    @Override
    public ParkingModel findParkingByIdWithSlotListAndDateRange(long id, long checkIn, long checkOut) {
        ParkingModel parkingModel = findParkingByIdWithSlotList(id);
        List<SlotModel> slotList = parkingModel.getSlots();
        for (SlotModel slotModel : slotList) {
            Optional<BookingModel> bookingModel = bookingService.findBookingByIdAndDateTimeRange(slotModel.getId(), checkIn, checkOut);
            if (bookingModel.isPresent()) {
                slotModel.setReserved(true);
            }
        }
        return parkingModel;
    }
}
