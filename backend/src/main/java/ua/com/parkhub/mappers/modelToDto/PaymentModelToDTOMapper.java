package ua.com.parkhub.mappers.modelToDTO;

import org.springframework.stereotype.Component;
import ua.com.parkhub.dto.PaymentDTO;
import ua.com.parkhub.mappers.Mapper;
import ua.com.parkhub.model.PaymentModel;

@Component
public class PaymentModelToDTOMapper implements Mapper<PaymentModel, PaymentDTO> {

    private final BookingModelToDTOMapper bookingModelToDTOMapper;


    public PaymentModelToDTOMapper(BookingModelToDTOMapper bookingModelToDTOMapper) {
        this.bookingModelToDTOMapper = bookingModelToDTOMapper;
    }

    @Override
    public PaymentDTO transform(PaymentModel from) {
        if(from == null) {
            return null;
        }
        PaymentDTO paymentDTO = new PaymentDTO();
        paymentDTO.setPrice(from.getPrice());
        paymentDTO.setBooking(bookingModelToDTOMapper.transform(from.getBooking()));
        return paymentDTO;
    }
}
