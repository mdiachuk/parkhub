package ua.com.parkhub.service.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import ua.com.parkhub.exceptions.BookingException;
import ua.com.parkhub.model.BookingModel;
import ua.com.parkhub.model.PaymentModel;
import ua.com.parkhub.persistence.impl.PaymentDAO;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

public class PaymentServiceTest {

    @Mock
    private PaymentDAO paymentDAO;

    @Mock
    private PaymentModel paymentModel;

    @Mock
    private BookingModel bookingModel;

    @InjectMocks
    private PaymentService paymentService;

    @BeforeEach
    public void init(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void findPaymentByBooking_positiveResult(){
        when(paymentDAO.findPaymentByBooking(bookingModel)).thenReturn(Optional.of(paymentModel));
        assertSame(paymentModel, paymentService.findPaymentByBooking(bookingModel));
    }

    @Test
    void findPaymentByBooking_exceptionExpected(){
        when(paymentDAO.findPaymentByBooking(null)).thenReturn(Optional.empty());
        assertThrows(BookingException.class, () -> paymentService.findPaymentByBooking(null));
    }

    @Test
    void findPriceIfCancelled_positiveResult(){
        when(paymentDAO.findPaymentByBooking(bookingModel)).thenReturn(Optional.of(paymentModel));
        assertSame(paymentModel.getPrice(), paymentService.findPriceIfCancelled(bookingModel));
    }

}
