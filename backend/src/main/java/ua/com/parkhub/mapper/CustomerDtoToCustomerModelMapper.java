package ua.com.parkhub.mapper;

import org.springframework.stereotype.Component;
import ua.com.parkhub.dto.CustomerDTO;
import ua.com.parkhub.mapper.Mapper;
import ua.com.parkhub.model.CustomerModel;
import ua.com.parkhub.model.SupportTicketModel;

import java.util.ArrayList;
import java.util.List;

@Component
public class    CustomerDtoToCustomerModelMapper implements Mapper<CustomerDTO, CustomerModel> {

    private final SupportTicketDtoToSupportTicketModelMapper supportTicketDtoToSupportTicketModelMapper;

    public CustomerDtoToCustomerModelMapper(
            SupportTicketDtoToSupportTicketModelMapper supportTicketDtoToSupportTicketModelMapper) {
        this.supportTicketDtoToSupportTicketModelMapper = supportTicketDtoToSupportTicketModelMapper;
    }

    @Override
    public CustomerModel transform(CustomerDTO from) {

        CustomerModel customerModel = new CustomerModel();
        customerModel.setId(from.getId());
        customerModel.setActive(from.isActive());
        customerModel.setPhoneNumber(from.getPhoneNumber());
        List<SupportTicketModel> list = null;
        if (from.getSupportTickets()!=null){
            list = new ArrayList<>();
            for (int i = 0; i < from.getSupportTickets().size(); i++) {
                list.add(supportTicketDtoToSupportTicketModelMapper.transform(from.getSupportTickets().get(i)));
            }
        }
        customerModel.setSupportTickets(list);

        return customerModel;
    }
}
