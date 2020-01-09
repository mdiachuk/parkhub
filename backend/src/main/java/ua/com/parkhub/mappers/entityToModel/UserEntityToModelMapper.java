package ua.com.parkhub.mappers.entityToModel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.com.parkhub.exceptions.ParkHubException;
import ua.com.parkhub.mappers.Mapper;
import ua.com.parkhub.model.SupportTicketModel;
import ua.com.parkhub.model.UserModel;
import ua.com.parkhub.persistence.entities.User;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserEntityToModelMapper implements Mapper<User, UserModel> {

    private CustomerEntityToModelMapper customerEntityToModelMapper;
    private RoleEntityToModelMapper roleEntityToModelMapper;
    private SupportTicketEntityToModelMapper supportTicketEntityToModelMapper;

    @Autowired
    public UserEntityToModelMapper(CustomerEntityToModelMapper customerEntityToModelMapper, RoleEntityToModelMapper roleEntityToModelMapper, SupportTicketEntityToModelMapper supportTicketEntityToModelMapper) {
        this.customerEntityToModelMapper = customerEntityToModelMapper;
        this.roleEntityToModelMapper = roleEntityToModelMapper;
        this.supportTicketEntityToModelMapper = supportTicketEntityToModelMapper;
    }

    @Override
    public UserModel transform(User entity) {
        if(entity == null) {
            throw new ParkHubException("User entity to be converted to UserModel is null.");
        }
        UserModel userModel = new UserModel();
        userModel.setEmail(entity.getEmail());
        userModel.setFirstName(entity.getFirstName());
        userModel.setLastName(entity.getLastName());
        userModel.setId(entity.getId());
        userModel.setPassword(entity.getPassword());
        userModel.setCustomer(customerEntityToModelMapper.transform(entity.getCustomer()));
        userModel.setNumberOfFailedPassEntering(entity.getNumberOfFailedPassEntering());
        List<SupportTicketModel> supportTicketModelList = entity.getTickets().stream().map(supportTicketEntityToModelMapper::transform).collect(Collectors.toList());
        userModel.setTickets(supportTicketModelList);
        userModel.setRole(roleEntityToModelMapper.transform(entity.getRole()));
        return userModel;
    }
}
