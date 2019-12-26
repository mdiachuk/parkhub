package ua.com.parkhub.mappers.toModel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.com.parkhub.mappers.Mapper;
import ua.com.parkhub.model.CustomerModel;
import ua.com.parkhub.persistence.entities.Customer;

import java.util.stream.Collectors;

@Component
public class CustomerEntityToModelMapper implements Mapper<Customer, CustomerModel> {

    BookingEntityToModelMapper bookingEntityToModelMapper;
    SupportTicketEntityToModelMapper supportTicketEntityToModelMapper;

    @Autowired
    public CustomerEntityToModelMapper(BookingEntityToModelMapper bookingEntityToModelMapper, SupportTicketEntityToModelMapper supportTicketEntityToModelMapper) {
        this.bookingEntityToModelMapper = bookingEntityToModelMapper;
        this.supportTicketEntityToModelMapper = supportTicketEntityToModelMapper;
    }

    @Override
    public CustomerModel transform(Customer from) {
        CustomerModel customerModel = new CustomerModel();
        customerModel.setId(from.getId());
        customerModel.setActive(from.isActive());
        customerModel.setPhoneNumber(from.getPhoneNumber());
        customerModel.setBookings(from.getBookings().stream().map(bookingEntityToModelMapper::transform).collect(Collectors.toSet()));
        customerModel.setSupportTickets(from.getSupportTickets().stream().map(supportTicketEntityToModelMapper::transform).collect(Collectors.toSet()));
        return customerModel;
    }
}
