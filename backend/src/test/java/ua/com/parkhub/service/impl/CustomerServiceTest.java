package ua.com.parkhub.service.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import ua.com.parkhub.exceptions.CustomerException;
import ua.com.parkhub.model.CustomerModel;
import ua.com.parkhub.persistence.impl.CustomerDAO;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;

public class CustomerServiceTest {

    @Mock
    private CustomerDAO customerDAO;

    @Mock
    private CustomerModel customerModel;

    @InjectMocks
    private CustomerService customerService;

    @BeforeEach
    public void init(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void findCustomerByPhoneNumber_positiveResult(){
        Mockito.when(customerDAO.findCustomerByPhoneNumber(anyString())).thenReturn(Optional.of(customerModel));
        assertSame(customerModel, customerService.findCustomerByPhoneNumber(anyString()));
    }

    @Test
    void findCustomerByPhoneNumber_customerExceptionExpected(){
        Mockito.when(customerDAO.findCustomerByPhoneNumber(null)).thenReturn(Optional.empty());
        assertThrows(CustomerException.class, () -> customerService.findCustomerByPhoneNumber(null));
    }
}
