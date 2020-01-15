package ua.com.parkhub.mappers.modelToDto;

import org.springframework.stereotype.Component;
import ua.com.parkhub.dto.BookingDTO;
import ua.com.parkhub.dto.PaymentDTO;
import ua.com.parkhub.mappers.Mapper;
import ua.com.parkhub.model.BookingModel;
import ua.com.parkhub.model.PaymentModel;

@Component
public class PaymentModelToDTOMapper implements Mapper<PaymentModel, PaymentDTO> {

    private final Mapper<BookingModel, BookingDTO> bookingModelToDTOMapper;

    public PaymentModelToDTOMapper(Mapper<BookingModel, BookingDTO> bookingModelToDTOMapper) {
        this.bookingModelToDTOMapper = bookingModelToDTOMapper;
    }

    @Override
    public PaymentDTO transform(PaymentModel from) {
        if (from == null) {
            return null;
        }
        PaymentDTO paymentDTO = new PaymentDTO();
        paymentDTO.setId(from.getId());
        paymentDTO.setPrice(from.getPrice());
        paymentDTO.setBooking(bookingModelToDTOMapper.transform(from.getBooking()));
        return paymentDTO;
    }
}
