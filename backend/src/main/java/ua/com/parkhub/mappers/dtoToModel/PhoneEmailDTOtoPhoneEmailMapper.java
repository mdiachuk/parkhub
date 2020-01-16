package ua.com.parkhub.mappers.dtoToModel;

import org.springframework.stereotype.Component;
import ua.com.parkhub.dto.PhoneEmailDTO;
import ua.com.parkhub.mappers.Mapper;
import ua.com.parkhub.model.PhoneEmailModel;

@Component
public class PhoneEmailDTOtoPhoneEmailMapper implements Mapper<PhoneEmailDTO, PhoneEmailModel> {

    @Override
    public PhoneEmailModel transform(PhoneEmailDTO from) {
        PhoneEmailModel phoneEmailModel = new PhoneEmailModel();
        phoneEmailModel.setEmail(from.getEmail());
        phoneEmailModel.setPhoneNumber(from.getPhoneNumber());
        return phoneEmailModel;
    }
}
