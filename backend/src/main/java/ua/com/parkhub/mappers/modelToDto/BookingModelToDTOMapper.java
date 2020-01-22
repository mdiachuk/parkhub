package ua.com.parkhub.mappers.modelToDto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.com.parkhub.dto.BookingDTO;
import ua.com.parkhub.dto.SlotDTO;
import ua.com.parkhub.mappers.Mapper;
import ua.com.parkhub.model.BookingModel;
import ua.com.parkhub.model.SlotModel;

@Component
public class BookingModelToDTOMapper implements Mapper<BookingModel, BookingDTO> {

    private Mapper<SlotModel, SlotDTO> slotModelToDTOMapper;

    @Autowired
    public BookingModelToDTOMapper(Mapper<SlotModel, SlotDTO> slotModelToDTOMapper) {
        this.slotModelToDTOMapper = slotModelToDTOMapper;
    }

    @Override
    public BookingDTO transform(BookingModel from) {
        if(from == null) {
            return null;
        }
        BookingDTO booking = new BookingDTO();
        booking.setCheckIn(from.getCheckIn());
        booking.setCheckOut(from.getCheckOut());
        booking.setSlot(slotModelToDTOMapper.transform(from.getSlot()));
        return booking;
    }
}
