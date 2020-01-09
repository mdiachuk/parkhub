package ua.com.parkhub.mappers.modelToEntity;

import org.springframework.stereotype.Component;
import ua.com.parkhub.exceptions.ParkHubException;
import ua.com.parkhub.mappers.Mapper;
import ua.com.parkhub.model.CustomerModel;
import ua.com.parkhub.persistence.entities.Customer;

@Component
public class CustomerModelToEntityMapper implements Mapper<CustomerModel, Customer> {

    @Override
    public Customer transform(CustomerModel model) {
        if(model == null) {
            throw new ParkHubException("CustomerModel entity to be converted to Customer is null.");
        }
        Customer customer = new Customer();
        customer.setId(model.getId());
        customer.setPhoneNumber(model.getPhoneNumber());
        customer.setActive(model.isActive());
//        customer.setBookings(null); // set when BookingMappers will be done
//        customer.setSupportTickets(null); // set TICKETS
        return customer;
    }
}
