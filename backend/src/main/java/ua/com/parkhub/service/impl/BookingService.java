package ua.com.parkhub.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.com.parkhub.exceptions.ParkHubException;
import ua.com.parkhub.model.Booking;
import ua.com.parkhub.model.Customer;
import ua.com.parkhub.model.Slot;
import ua.com.parkhub.persistence.entities.BookingEntity;
import ua.com.parkhub.persistence.entities.SlotEntity;
import ua.com.parkhub.persistence.impl.BookingDAO;
import ua.com.parkhub.persistence.impl.SlotDAO;
import ua.com.parkhub.service.IBookingService;
import ua.com.parkhub.service.ICustomerService;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class BookingService implements IBookingService {

    private final BookingDAO bookingDAO;
    private final SlotDAO slotDAO;
    private final ICustomerService customerService;
    private final ModelMapper mapper;

    @Autowired
    public BookingService(BookingDAO bookingDAO, SlotDAO slotDAO, ICustomerService customerService, ModelMapper mapper) {
        this.bookingDAO = bookingDAO;
        this.slotDAO = slotDAO;
        this.customerService = customerService;
        this.mapper = mapper;
    }

    private Slot findSlotByIdAndUpdate(long slotId) {
        Optional<SlotEntity> optionalSlot = slotDAO.findElementById(slotId);
        if (optionalSlot.isPresent()) {
            SlotEntity slotEntity = optionalSlot.get();
            slotEntity.setReserved(true);
            slotDAO.updateElement(slotEntity);
            return mapper.map(slotEntity, Slot.class);
        } else {
            throw new ParkHubException("No available slot found by id :" + slotId);
        }
    }

    @Transactional
    public Booking addBooking(String carNumber, String phoneNumber, long slotId) {
        Booking booking = new Booking();
        Customer customer = customerService.findCustomerByPhoneNumberOrAdd(phoneNumber);
        booking.setCustomer(customer);
        System.out.println(customer);
        booking.setCarNumber(carNumber);
        System.out.println(carNumber);
        Slot slot = findSlotByIdAndUpdate(slotId);
        booking.setSlot(slot);
        booking.setCheckIn(LocalDateTime.now());
        booking.setActive(true);
        BookingEntity bookingEntity = mapper.map(booking, BookingEntity.class);
        bookingDAO.addElement(bookingEntity);
        return booking;
    }
}
