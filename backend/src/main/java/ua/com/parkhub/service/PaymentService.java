package ua.com.parkhub.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.com.parkhub.persistence.entities.Booking;
import ua.com.parkhub.persistence.entities.Customer;
import ua.com.parkhub.persistence.entities.Payment;
import ua.com.parkhub.persistence.impl.BookingDAO;
import ua.com.parkhub.persistence.impl.CustomerDAO;
import ua.com.parkhub.persistence.impl.PaymentDAO;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Optional;

@Service
public class PaymentService {

    private BookingDAO bookingDAO;
    private CustomerDAO customerDAO;
    private PaymentDAO paymentDAO;

    @Autowired
    public PaymentService(BookingDAO bookingDAO,
                          CustomerDAO customerDAO,
                          PaymentDAO paymentDAO) {
        this.bookingDAO = bookingDAO;
        this.customerDAO = customerDAO;
        this.paymentDAO = paymentDAO;
    }

    @Transactional
    public Optional<Customer> checkCustomerIfPresent(String phoneNumber) {
        return  customerDAO.findElementByPhone(phoneNumber);
    }

    private int countPrice(Booking booking){
        if (booking.getCheckOut() != null){
            int hours = (int) ChronoUnit.HOURS.between(booking.getCheckIn(), booking.getCheckOut());
            int tariff = booking.getSlot().getParking().getTariff();
            return hours * tariff;
        }else {
            return 0;
        }
    }

    private Optional<Payment> createPayment(String phoneNumber){
        Customer customer = checkCustomerIfPresent(phoneNumber).get();
        Booking booking = bookingDAO.findActiveBookingByCustomer(customer).get();
        booking.setCheck_out(LocalDateTime.now());
        booking.getSlot().setActive(false);
        booking.setActive(false);
        bookingDAO.updateElement(booking);
        int price = countPrice(booking);
        Payment payment = new Payment();
        payment.setBooking(booking);
        payment.setPaid(false);
        payment.setPrice(price);
        paymentDAO.addElement(payment);
        return Optional.of(payment);
    }

    @Transactional
    public int getPrice(String phoneNumber) {
        return createPayment(phoneNumber).get().getPrice();
    }
}
