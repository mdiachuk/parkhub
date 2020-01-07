package ua.com.parkhub.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.com.parkhub.exceptions.BookingException;
import ua.com.parkhub.exceptions.StatusCode;
import ua.com.parkhub.model.BookingModel;
import ua.com.parkhub.model.CustomerModel;
import ua.com.parkhub.model.PaymentModel;
import ua.com.parkhub.persistence.impl.BookingDAO;
import ua.com.parkhub.persistence.impl.SlotDAO;
import ua.com.parkhub.service.IBookingService;
import ua.com.parkhub.service.ICustomerService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class BookingService implements IBookingService {

    private final BookingDAO bookingDAO;
    private final SlotDAO slotDAO;
    private final ICustomerService customerService;
    private final PaymentService paymentService;
    private int price;

    @Autowired
    public BookingService(PaymentService paymentService, BookingDAO bookingDAO, SlotDAO slotDAO, ICustomerService customerService) {
        this.bookingDAO = bookingDAO;
        this.slotDAO = slotDAO;
        this.customerService = customerService;
        this.paymentService = paymentService;
    }

    /*private ua.com.parkhub.model.Slot findSlotByIdAndUpdate(long slotId) {
        Optional<Slot> optionalSlot = slotDAO.findElementById(slotId);
        if (optionalSlot.isPresent()) {
            Slot slot = optionalSlot.get();
            if (slot.isActive() && !slot.isReserved()) {
                slot.setReserved(true);
                slotDAO.updateElement(slot);
                return mapper.map(slot, ua.com.parkhub.model.Slot.class);
            }
            throw new ParkHubException("Unfortunately this slot is temporary unavailable");
        }
        throw new ParkHubException("No available slot found by id :" + slotId);
    }

    @Transactional
    public ua.com.parkhub.model.Booking addBooking(String carNumber, String phoneNumber, long slotId) {
        ua.com.parkhub.model.Booking booking = new ua.com.parkhub.model.Booking();
        ua.com.parkhub.model.Customer customer = customerService.findCustomerByPhoneNumberOrAdd(phoneNumber);
        booking.setCustomer(customer);
        booking.setCarNumber(carNumber);
        ua.com.parkhub.model.Slot slot = findSlotByIdAndUpdate(slotId);
        booking.setSlot(slot);
        booking.setCheckIn(LocalDateTime.now());
        booking.setActive(true);
        Booking bookingEntity = mapper.map(booking, Booking.class);
        bookingDAO.addElement(bookingEntity);
        return booking;
    }*/

    public Optional<BookingModel> findPrepaidBooking(CustomerModel customerModel){
        LocalDateTime now = LocalDateTime.now();
        BookingModel bookingModel;
        List<BookingModel> bookingModels = bookingDAO.findBookingsByCustomer(customerModel);
        bookingModel = bookingModels.stream()
                .filter((x -> x.getCheckIn().compareTo(now) > 0 && x.isActive())).findFirst()
                .orElseThrow(()-> new BookingException(StatusCode.BOOKING_NOT_FOUND));
        return Optional.ofNullable(bookingModel);
    }

    public int findPrice(String phoneNumber){
        Optional<BookingModel> bookingModel = findPrepaidBooking(customerService.findByPhoneNumber(phoneNumber));
        if (bookingModel.isPresent()){
            BookingModel bm = bookingModel.get();
            bm.setCheckOut(LocalDateTime.now());
            bm.setActive(false);
            PaymentModel paymentModel = paymentService.findPaymentByBooking(bm);
            price = paymentModel.getPrice();
            paymentModel.setCancelled(true);
            paymentService.getPaymentDAO().updateElement(paymentModel);
            bookingDAO.updateElement(bm);
            return price;
        }else {
            throw new BookingException(StatusCode.BOOKING_NOT_FOUND);
        }
    }

    @Override
    public Booking addBooking(String phoneNumber, String carNumber, long slotId) {
        return null;
    }
}
