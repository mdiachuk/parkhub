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
    private static final int CANADIAN_DOLLAR = 21;
    private static final int MAX_VALUE = 9;
    private static final int MIN_VALUE = 1;

    public PaymentModelToDTOMapper(Mapper<BookingModel, BookingDTO> bookingModelToDTOMapper) {
        this.bookingModelToDTOMapper = bookingModelToDTOMapper;
    }

    private int convertToCanadianDollars(int price) {
        int total = price / CANADIAN_DOLLAR;
        return total > 0 && total < MAX_VALUE ? total : MIN_VALUE;
    }

    @Override
    public PaymentDTO transform(PaymentModel from) {
        if (from == null) {
            return null;
        }
        PaymentDTO paymentDTO = new PaymentDTO();
        paymentDTO.setId(from.getId());
        paymentDTO.setPrice(convertToCanadianDollars(from.getPrice()));
        paymentDTO.setBooking(bookingModelToDTOMapper.transform(from.getBooking()));
        return paymentDTO;
    }
}
