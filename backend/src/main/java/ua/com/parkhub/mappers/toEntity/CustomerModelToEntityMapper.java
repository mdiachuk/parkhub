package ua.com.parkhub.mappers.toEntity;

import org.springframework.stereotype.Component;
import ua.com.parkhub.mapper.Mapper;
import ua.com.parkhub.model.CustomerModel;
import ua.com.parkhub.persistence.entities.Customer;

@Component
public class CustomerModelToEntityMapper implements Mapper<CustomerModel, Customer> {

    private final SupportTicketModelToEntityMapper supportTicketModelToEntityMapper;

    public CustomerModelToEntityMapper(SupportTicketModelToEntityMapper supportTicketModelToEntityMapper) {
        this.supportTicketModelToEntityMapper = supportTicketModelToEntityMapper;
    }

    @Override
    public Customer transform(CustomerModel from) {
        Customer customer = new Customer();
        customer.setPhoneNumber(from.getPhoneNumber());
        customer.setActive(true);
        customer.setId(from.getId());
//        List <SupportTicket> list = new ArrayList<>();
//        for (int i = 0; i <from.getSupportTickets().size() ; i++) {
//            list.add(supportTicketModelToEntityMapper.transform(from.getSupportTickets().get(i)));
//        }
//        customer.setSupportTickets(null);
//        customer.setBookings(null);
        return customer;
    }
}
