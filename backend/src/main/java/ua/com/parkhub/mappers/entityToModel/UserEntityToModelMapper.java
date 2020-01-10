package ua.com.parkhub.mappers.entityToModel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.com.parkhub.mappers.Mapper;
import ua.com.parkhub.model.UserModel;
import ua.com.parkhub.persistence.entities.User;

import java.util.stream.Collectors;

@Component
public class UserEntityToModelMapper implements Mapper<User, UserModel> {

    CustomerEntityToModelMapper customerEntityToModelMapper;
    RoleEntityToModelMapper roleEntityToModelMapper;
    SupportTicketEntityToModelMapper supportTicketEntityToModelMapper;

    @Autowired
    public UserEntityToModelMapper(CustomerEntityToModelMapper customerEntityToModelMapper, RoleEntityToModelMapper roleEntityToModelMapper, SupportTicketEntityToModelMapper supportTicketEntityToModelMapper) {
        this.customerEntityToModelMapper = customerEntityToModelMapper;
        this.roleEntityToModelMapper = roleEntityToModelMapper;
        this.supportTicketEntityToModelMapper = supportTicketEntityToModelMapper;
    }


    @Override
    public UserModel transform(User from) {
        if(from == null) {
            return null;
        }
        UserModel userModel = new UserModel();
        userModel.setEmail(from.getEmail());
        userModel.setFirstName(from.getFirstName());
        userModel.setLastName(from.getLastName());
        userModel.setId(from.getId());
        userModel.setPassword(from.getPassword());
        userModel.setCustomer(customerEntityToModelMapper.transform(from.getCustomer()));
        userModel.setNumberOfFailedPassEntering(from.getNumberOfFailedPassEntering());
        if(from.getTickets() != null){
            userModel.setTickets(from.getTickets().stream().map(supportTicketEntityToModelMapper::transform).collect(Collectors.toList()));
        }
        userModel.setRole(roleEntityToModelMapper.transform(from.getRole()));
        return userModel;
    }
}
