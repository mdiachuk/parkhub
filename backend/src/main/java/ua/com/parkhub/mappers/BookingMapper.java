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
        return Optional.of(new Booking(model.getCarNumber(),
                model.getCheckIn(),
                model.getCheckOut(),
                model.isActive(),
                customerMapper.toEntity(model.getCustomer()).get(),
                slotMapper.toEntity(model.getSlot()).get()));
    }

    @Override
    public Optional<BookingModel> toModel(Booking entity) {
        return Optional.of(new BookingModel(customerMapper.toModel(entity.getCustomer()).get(),
                entity.getCarNumber(),
                slotMapper.toModel(entity.getSlot()).get(),
                entity.getCheckIn(),
                entity.getCheckOut(),
                entity.isActive()));
    }
}
