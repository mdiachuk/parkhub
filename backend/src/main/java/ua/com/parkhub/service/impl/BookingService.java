package ua.com.parkhub.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.com.parkhub.exceptions.ParkHubException;
import ua.com.parkhub.exceptions.StatusCode;
import ua.com.parkhub.model.*;
import ua.com.parkhub.exceptions.BookingException;
import ua.com.parkhub.persistence.entities.Slot;
import ua.com.parkhub.persistence.impl.BookingDAO;
import ua.com.parkhub.persistence.impl.SlotDAO;
import ua.com.parkhub.service.IBookingService;
import ua.com.parkhub.service.ICustomerService;
import ua.com.parkhub.service.IPaymentService;
import ua.com.parkhub.util.formatter.DateFormatter;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class BookingService implements IBookingService {

    private final BookingDAO bookingDAO;
    private final SlotDAO slotDAO;
    private final ICustomerService customerService;
    private final IPaymentService paymentService;
    private int price;

    @Autowired
    public BookingService(BookingDAO bookingDAO, SlotDAO slotDAO, ICustomerService customerService, IPaymentService paymentService) {
        this.bookingDAO = bookingDAO;
        this.slotDAO = slotDAO;
        this.customerService = customerService;
        this.paymentService = paymentService;
    }

    private SlotModel findSlotById(long slotId) {
        return slotDAO.findElementById(slotId).orElseThrow(() -> (new ParkHubException("No available slot found by id :" + slotId)));
    }

    @Transactional
    public PaymentModel addBooking(String carNumber, String phoneNumber, long slotId, long checkIn, long checkOut, int price) {
        BookingModel booking = new BookingModel();
        CustomerModel customer = customerService.findCustomerByPhoneNumberOrAdd(phoneNumber);
        booking.setCustomer(customer);
        booking.setCarNumber(carNumber);
        SlotModel slot = findSlotById(slotId);
        booking.setSlot(slot);
        LocalDateTime from = DateFormatter.covertMillisToLocalDateTime(checkIn);
        booking.setCheckIn(from);
        LocalDateTime to = DateFormatter.covertMillisToLocalDateTime(checkOut);
        booking.setCheckOut(to);
        booking.setActive(true);
        BookingModel bookingModel = bookingDAO.addWithResponse(booking);
        return paymentService.addPayment(bookingModel, price);
    }

    @Transactional(readOnly = true)
    public Optional<BookingModel> findBookingByIdAndDateTimeRange(long id, long checkIn, long checkOut) {
        LocalDateTime localDateTimeCheckIn = new Timestamp(checkIn).toLocalDateTime();
        LocalDateTime localDateTimeCheckOut = new Timestamp(checkOut).toLocalDateTime();
        String fieldNameId = "slot";
        String fieldNameCheckIn = "checkIn";
        String fieldNameCheckOut = "checkOut";
        return bookingDAO.findElementByFieldsEqual(id, localDateTimeCheckIn, localDateTimeCheckOut, fieldNameId, fieldNameCheckIn, fieldNameCheckOut);
    }

    public Optional<BookingModel> findPrepaidBooking(CustomerModel customerModel){
        BookingModel bookingModel;
        List<BookingModel> bookingModels = bookingDAO.findBookingsByCustomer(customerModel);
        bookingModel = bookingModels.stream()
                .filter(((x) -> x.getCheckIn().compareTo(LocalDateTime.now()) > 0 && x.isActive()))
                .findFirst().orElseThrow(()-> new BookingException(StatusCode.BOOKING_NOT_FOUND));
        return Optional.of(bookingModel);
    }

    @Override
    public List<Slot> findAllAvailableSlots(long checkIn, long checkOut) {
        return bookingDAO.findElementsByFieldsEqual(checkIn, checkOut);
    }

    public int findPrice(String phoneNumber) {
        Optional<BookingModel> bookingModel = findPrepaidBooking(customerService.findCustomerByPhoneNumber(phoneNumber));
        return bookingModel.map(bm -> {
            bm.setCheckOut(LocalDateTime.now());
            bm.setActive(false);
            price = paymentService.findPriceIfCancelled(bm);
            bookingDAO.updateElement(bm);
            return price;
        }).orElseThrow(() -> new BookingException(StatusCode.BOOKING_NOT_FOUND));
    }
}
