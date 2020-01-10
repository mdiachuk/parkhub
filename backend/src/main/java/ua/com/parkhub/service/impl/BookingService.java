//package ua.com.parkhub.service.impl;
//
//import org.modelmapper.ModelMapper;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//import ua.com.parkhub.exceptions.ParkHubException;
//import ua.com.parkhub.persistence.entities.Booking;
//import ua.com.parkhub.persistence.entities.Slot;
//import ua.com.parkhub.persistence.impl.BookingDAO;
//import ua.com.parkhub.persistence.impl.SlotDAO;
//import ua.com.parkhub.service.IBookingService;
//import ua.com.parkhub.service.ICustomerService;
//
//import java.time.LocalDateTime;
//import java.util.Optional;
//
//@Service
//public class BookingService implements IBookingService {
//
//    private final BookingDAO bookingDAO;
//    private final SlotDAO slotDAO;
//    private final ICustomerService customerService;
//    private final ModelMapper mapper;
//
//    @Autowired
//    public BookingService(BookingDAO bookingDAO, SlotDAO slotDAO, ICustomerService customerService, ModelMapper mapper) {
//        this.bookingDAO = bookingDAO;
//        this.slotDAO = slotDAO;
//        this.customerService = customerService;
//        this.mapper = mapper;
//    }
//
//    private ua.com.parkhub.model.Slot findSlotByIdAndUpdate(long slotId) {
//        Optional<Slot> optionalSlot = slotDAO.findElementById(slotId);
//        if (optionalSlot.isPresent()) {
//            Slot slot = optionalSlot.get();
//            if (slot.isActive() && !slot.isReserved()) {
//                slot.setReserved(true);
//                slotDAO.updateElement(slot);
//                return mapper.map(slot, ua.com.parkhub.model.Slot.class);
//            }
//            throw new ParkHubException("Unfortunately this slot is temporary unavailable");
//        }
//        throw new ParkHubException("No available slot found by id :" + slotId);
//    }
//
//    @Transactional
//    public ua.com.parkhub.model.Booking addBooking(String carNumber, String phoneNumber, long slotId) {
//        ua.com.parkhub.model.Booking booking = new ua.com.parkhub.model.Booking();
//        ua.com.parkhub.model.Customer customer = customerService.findCustomerByPhoneNumberOrAdd(phoneNumber);
//        booking.setCustomer(customer);
//        booking.setCarNumber(carNumber);
//        ua.com.parkhub.model.Slot slot = findSlotByIdAndUpdate(slotId);
//        booking.setSlot(slot);
//        booking.setCheckIn(LocalDateTime.now());
//        booking.setActive(true);
//        Booking bookingEntity = mapper.map(booking, Booking.class);
//        bookingDAO.addElement(bookingEntity);
//        return booking;
//    }
//}
