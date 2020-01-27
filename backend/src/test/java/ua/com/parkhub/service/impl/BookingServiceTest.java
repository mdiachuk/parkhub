package ua.com.parkhub.service.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import ua.com.parkhub.exceptions.BookingException;
import ua.com.parkhub.model.BookingModel;
import ua.com.parkhub.model.CustomerModel;
import ua.com.parkhub.model.PaymentModel;
import ua.com.parkhub.persistence.impl.BookingDAO;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;


public class BookingServiceTest {

    @Mock
    private BookingDAO bookingDAO;

    @Mock
    private PaymentService paymentService;

    @InjectMocks
    private BookingService bookingService;

    @BeforeEach
    public void init(){
        MockitoAnnotations.initMocks(this);
    }


    @Test
    void findPrepaidBooking_PositiveResult(){
        CustomerModel customerModel = new CustomerModel();
        List<BookingModel> bookingModels = new ArrayList<>();
        BookingModel bookingModel = new BookingModel();
        bookingModel.setActive(true);
        bookingModel.setCheckIn(LocalDateTime.now().plusDays(1));
        bookingModel.setCustomer(customerModel);
        bookingModels.add(bookingModel);
        when(bookingDAO.findBookingsByCustomer(customerModel)).thenReturn(bookingModels);
        assertEquals(Optional.of(bookingModel), bookingService.findPrepaidBooking(customerModel));
    }

    @Test
    void findPrepaidBooking_throwsException(){
        CustomerModel customerModel = new CustomerModel();
        List<BookingModel> bookingModels = new ArrayList<>();
        BookingModel bookingModel = new BookingModel();
        bookingModel.setActive(true);
        bookingModel.setCheckIn(LocalDateTime.now().plusDays(1));
        bookingModel.setCustomer(customerModel);
        bookingModels.add(bookingModel);
        when(bookingDAO.findBookingsByCustomer(any(CustomerModel.class))).thenReturn(bookingModels);
        assertThrows(BookingException.class, () -> bookingService.findPrepaidBooking(null));
    }

    @Test
    void findPrice_positiveResult(){
        CustomerModel customerModel = new CustomerModel();
        customerModel.setPhoneNumber("");
        BookingService bookingService = Mockito.mock(BookingService.class);
        List<BookingModel> bookingModels = new ArrayList<>();
        BookingModel bookingModel = new BookingModel();
        bookingModel.setActive(true);
        bookingModel.setCheckIn(LocalDateTime.now().plusDays(1));
        bookingModel.setCustomer(customerModel);
        bookingModels.add(bookingModel);
        PaymentModel paymentModel = new PaymentModel();
        paymentModel.setBooking(bookingModel);
        when(bookingDAO.findBookingsByCustomer(customerModel)).thenReturn(bookingModels);
        doReturn(Optional.of(bookingModel)).when(bookingService).findPrepaidBooking(customerModel);
        when(paymentService.findPriceIfCancelled(bookingModel)).thenReturn(paymentModel.getPrice());
        assertEquals(paymentModel.getPrice(), bookingService.findPrice(customerModel.getPhoneNumber()));
    }
}
