package ua.com.parkhub.mappers.toDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.com.parkhub.dto.BookingDTO;
import ua.com.parkhub.mappers.Mapper;
import ua.com.parkhub.model.BookingModel;

@Component
public class BookingModelToDTOMapper implements Mapper<BookingModel, BookingDTO> {

    private final SlotModelToDTOMapper slotModelToDTOMapper;

    @Autowired
    public BookingModelToDTOMapper(SlotModelToDTOMapper slotModelToDTOMapper) {
        this.slotModelToDTOMapper = slotModelToDTOMapper;
    }

    @Override
    public BookingDTO transform(BookingModel from) {
        BookingDTO booking = new BookingDTO();
        booking.setCheckIn(from.getCheckIn());
        booking.setSlot(slotModelToDTOMapper.transform(from.getSlot()));
        return booking;
    }
}
