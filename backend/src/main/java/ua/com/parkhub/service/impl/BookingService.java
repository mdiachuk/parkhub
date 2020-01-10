package ua.com.parkhub.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.com.parkhub.exceptions.ParkHubException;
import ua.com.parkhub.exceptions.SlotException;
import ua.com.parkhub.model.BookingModel;
import ua.com.parkhub.model.CustomerModel;
import ua.com.parkhub.model.PaymentModel;
import ua.com.parkhub.model.SlotModel;
import ua.com.parkhub.persistence.entities.Payment;
import ua.com.parkhub.persistence.impl.BookingDAO;
import ua.com.parkhub.persistence.impl.SlotDAO;
import ua.com.parkhub.service.IBookingService;
import ua.com.parkhub.service.ICustomerService;
import ua.com.parkhub.service.IParkingService;
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
        return slotDAO.findElementById(slotId).orElseThrow(() -> (new ParkHubException("No available slot found by id :" + slotId)));
    }

    @Transactional
    public PaymentModel addBooking(String carNumber, String phoneNumber, long slotId, long checkIn, long checkOut, int price) {
        BookingModel booking = new BookingModel();
        CustomerModel customer = customerService.findCustomerByPhoneNumber(phoneNumber);
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
}
