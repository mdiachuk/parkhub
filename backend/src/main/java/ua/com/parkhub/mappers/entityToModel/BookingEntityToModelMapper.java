package ua.com.parkhub.mappers.entityToModel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.com.parkhub.mappers.Mapper;
import ua.com.parkhub.model.BookingModel;
import ua.com.parkhub.persistence.entities.Booking;

@Component
public class BookingEntityToModelMapper implements Mapper<Booking, BookingModel> {

    private final SlotEntityToModelMapper slotEntityToModelMapper;
    private final CustomerEntityToModelMapper customerEntityToModelMapper;

    @Autowired
    public BookingEntityToModelMapper(SlotEntityToModelMapper slotEntityToModelMapper, CustomerEntityToModelMapper customerEntityToModelMapper) {
        this.slotEntityToModelMapper = slotEntityToModelMapper;
        this.customerEntityToModelMapper = customerEntityToModelMapper;
    }

    @Override
    public BookingModel transform(Booking from) {
        if(from == null) {
            return null;
        }
        BookingModel bookingModel = new BookingModel();
        bookingModel.setId(from.getId());
        bookingModel.setActive(from.isActive());
        bookingModel.setCarNumber(from.getCarNumber());
        bookingModel.setCheckIn(from.getCheckIn());
        bookingModel.setCheckOut(from.getCheckOut());
        bookingModel.setSlot(slotEntityToModelMapper.transform(from.getSlot()));
        bookingModel.setCustomer(customerEntityToModelMapper.transform(from.getCustomer()));
        return bookingModel;
    }
}
