package ua.com.parkhub.mappers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.com.parkhub.model.BookingModel;
import ua.com.parkhub.persistence.entities.Booking;

import java.util.Optional;

@Component
public class BookingMapper implements Mapper<Booking, BookingModel> {

    private CustomerMapper customerMapper;
    private SlotMapper slotMapper;

    @Autowired
    public BookingMapper(CustomerMapper customerMapper, SlotMapper slotMapper) {
        this.customerMapper = customerMapper;
        this.slotMapper = slotMapper;
    }

    @Override
    public Optional<Booking> toEntity(BookingModel model) {
        Booking booking = new Booking();
        booking.setCarNumber(model.getCarNumber());
        booking.setCheckIn(model.getCheckIn());
        booking.setCheck_out(model.getCheckOut());
        booking.setActive(model.isActive());
        booking.setCustomer(customerMapper.toEntity(model.getCustomer()).get());
        booking.setSlot(slotMapper.toEntity(model.getSlot()).get());
        return Optional.of(booking);
    }

    @Override
    public Optional<BookingModel> toModel(Booking entity) {
        BookingModel bookingModel = new BookingModel();
        bookingModel.setCustomer(customerMapper.toModel(entity.getCustomer()).get());
        bookingModel.setSlot(slotMapper.toModel(entity.getSlot()).get());
        bookingModel.setCarNumber(entity.getCarNumber());
        bookingModel.setCheckIn(entity.getCheckIn());
        bookingModel.setCheckOut(entity.getCheckOut());
        bookingModel.setActive(entity.isActive());
        return Optional.of(bookingModel);
    }
}
