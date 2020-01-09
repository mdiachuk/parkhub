package ua.com.parkhub.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.com.parkhub.exceptions.ParkHubException;
import ua.com.parkhub.model.BookingModel;
import ua.com.parkhub.model.CustomerModel;
import ua.com.parkhub.model.SlotModel;
import ua.com.parkhub.persistence.impl.BookingDAO;
import ua.com.parkhub.persistence.impl.SlotDAO;
import ua.com.parkhub.service.IBookingService;
import ua.com.parkhub.service.ICustomerService;
import ua.com.parkhub.service.IPaymentService;
import ua.com.parkhub.util.formatter.DateFormatter;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class BookingService implements IBookingService {
    private final BookingDAO bookingDAO;
    private final SlotDAO slotDAO;
    private final ICustomerService customerService;
    private final IPaymentService paymentService;

    @Autowired
    public BookingService(BookingDAO bookingDAO, SlotDAO slotDAO, ICustomerService customerService, IPaymentService paymentService) {
        this.bookingDAO = bookingDAO;
        this.slotDAO = slotDAO;
        this.customerService = customerService;
        this.paymentService = paymentService;
    }

    private SlotModel findSlotById(long slotId) {
        Optional<SlotModel> optionalSlot = slotDAO.findElementById(slotId);
        if (optionalSlot.isPresent()) {
            SlotModel slot = optionalSlot.get();
            if (slot.isActive() && !slot.isReserved()) {
                return slot;
            }
            throw new ParkHubException("Unfortunately this slot is temporary unavailable");
        }
        throw new ParkHubException("No available slot found by id :" + slotId);
    }

    @Transactional
    public BookingModel addBooking(String carNumber, String phoneNumber, long slotId, long checkIn, long checkOut) {
        BookingModel booking = new BookingModel();
        CustomerModel customer = customerService.findCustomerByPhoneNumberOrAdd(phoneNumber);
        booking.setCustomer(customer);
        booking.setCarNumber(carNumber);
        SlotModel slot = findSlotById(slotId);
        booking.setSlot(slot);
        LocalDateTime from = DateFormatter.covertMillisToLocalDateTime(checkIn);
        System.out.println("FROM : " + from);
        booking.setCheckIn(from);
        LocalDateTime to = DateFormatter.covertMillisToLocalDateTime(checkOut);
        System.out.println("TO : " + to);
        booking.setCheckOut(to);
        booking.setActive(true);
        /*bookingDAO.addElement(booking);*/
        BookingModel bookingModel = bookingDAO.addWithResponse(booking);
        paymentService.addPayment(bookingModel);
        return bookingModel;
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
