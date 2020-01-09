package ua.com.parkhub.service.impl;


import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.com.parkhub.exceptions.ParkHubException;
import ua.com.parkhub.model.BookingModel;
import ua.com.parkhub.model.CustomerModel;
import ua.com.parkhub.model.ParkingModel;
import ua.com.parkhub.model.SlotModel;
import ua.com.parkhub.persistence.impl.BookingDAO;
import ua.com.parkhub.persistence.impl.SlotDAO;
import ua.com.parkhub.service.IBookingService;
import ua.com.parkhub.service.ICustomerService;
import ua.com.parkhub.util.formatter.DateFormatter;

import java.lang.reflect.Field;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookingService implements IBookingService {
    private final BookingDAO bookingDAO;
    private final SlotDAO slotDAO;
    private final ICustomerService customerService;

    @Autowired
    public BookingService(BookingDAO bookingDAO, SlotDAO slotDAO, ICustomerService customerService) {
        this.bookingDAO = bookingDAO;
        this.slotDAO = slotDAO;
        this.customerService = customerService;
    }

    private SlotModel findSlotByIdAndUpdate(long slotId) {
        Optional<SlotModel> optionalSlot = slotDAO.findElementById(slotId);
        if (optionalSlot.isPresent()) {
            SlotModel slot = optionalSlot.get();
            if (slot.isActive() && !slot.isReserved()) {
                slot.setReserved(true);
                slotDAO.updateElement(slot);
                return slot;
            }
            throw new ParkHubException("Unfortunately this slot is temporary unavailable");
        }
        throw new ParkHubException("No available slot found by id :" + slotId);
    }

    @Transactional
    public BookingModel addBooking(String carNumber, String phoneNumber, long slotId) {
        BookingModel booking = new BookingModel();
        CustomerModel customer = customerService.findCustomerByPhoneNumberOrAdd(phoneNumber);
        booking.setCustomer(customer);
        booking.setCarNumber(carNumber);
        SlotModel slot = findSlotByIdAndUpdate(slotId);
        booking.setSlot(slot);
        booking.setCheckIn(LocalDateTime.now());
        booking.setActive(true);
        bookingDAO.addElement(booking);
        return booking;
    }

    @Transactional(readOnly = true)
    public Optional<BookingModel> findBookingByIdAndDateTimeRange(long id, long checkIn, long checkOut) {
        LocalDateTime localDateTimeCheckIn = new Timestamp(checkIn).toLocalDateTime();
        System.out.println(localDateTimeCheckIn);
        LocalDateTime localDateTimeCheckOut = new Timestamp(checkOut).toLocalDateTime();
        System.out.println(localDateTimeCheckOut);
        String fieldNameId = "slot";
        String fieldNameCheckIn = "checkIn";
        String fieldNameCheckOut = "checkOut";
        return bookingDAO.findElementByFieldsEqual(id, localDateTimeCheckIn, localDateTimeCheckOut, fieldNameId, fieldNameCheckIn, fieldNameCheckOut);
    }
}
