package ua.com.parkhub.mappers.fromDtoToModel;

import org.springframework.stereotype.Component;
import ua.com.parkhub.dto.UserDTO;
import ua.com.parkhub.mappers.Mapper;
import ua.com.parkhub.model.SupportTicketModel;
import ua.com.parkhub.model.UserModel;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserDtoToUserModelMapper implements Mapper<UserDTO, UserModel> {

    private final CustomerDtoToCustomerModelMapper customerDtoToCustomerModelMapper;
    private final RoleDtoToRoleModelMapper roleDtoToRoleModelMapper;
//    private final SupportTicketDtoToSupportTicketModelMapper supportTicketDtoToSupportTicketModelMapper;

    public UserDtoToUserModelMapper(CustomerDtoToCustomerModelMapper customerDtoToCustomerModelMapper,
                                    RoleDtoToRoleModelMapper roleDtoToRoleModelMapper/*,
                                    SupportTicketDtoToSupportTicketModelMapper supportTicketDtoToSupportTicketModelMapper*/) {
        this.customerDtoToCustomerModelMapper = customerDtoToCustomerModelMapper;
        this.roleDtoToRoleModelMapper = roleDtoToRoleModelMapper;
//        this.supportTicketDtoToSupportTicketModelMapper = supportTicketDtoToSupportTicketModelMapper;
    }

    @Override
    public UserModel transform(UserDTO from) {

        UserModel userModel = new UserModel();
        userModel.setPassword(from.getPassword());
        userModel.setEmail(from.getEmail());

        userModel.setCustomer(customerDtoToCustomerModelMapper.transform(from.getCustomer()));

        userModel.setFirstName(from.getFirstName());
        userModel.setLastName(from.getLastName());
        userModel.setId(from.getId());
//        userModel.setNumberOfFaildPassEntering(from.getNumberOfFaildPassEntering());
        userModel.setRole(roleDtoToRoleModelMapper.transform(from.getRole()));

//        List<SupportTicketModel> list = null;
//        if (from.getTickets()!=null) {
//            list = new ArrayList<>();
//            for (int i = 0; i < from.getTickets().size(); i++) {
//                list.add(supportTicketDtoToSupportTicketModelMapper.transform(from.getTickets().get(i)));
//            }
//        }
//
//        userModel.setTickets(list);
        userModel.setToken(from.getToken());


        return userModel;
    }
}
