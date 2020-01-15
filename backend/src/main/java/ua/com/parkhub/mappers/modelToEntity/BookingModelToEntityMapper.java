package ua.com.parkhub.mappers.modelToEntity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.com.parkhub.mappers.Mapper;
import ua.com.parkhub.model.BookingModel;
import ua.com.parkhub.model.CustomerModel;
import ua.com.parkhub.model.SlotModel;
import ua.com.parkhub.persistence.entities.Booking;
import ua.com.parkhub.persistence.entities.Customer;
import ua.com.parkhub.persistence.entities.Slot;

@Component
public class BookingModelToEntityMapper implements Mapper<BookingModel, Booking> {

    private Mapper<SlotModel, Slot> slotModelToEntityMapper;
    private Mapper<CustomerModel, Customer> customerModelToEntityMapper;

    @Autowired
    public BookingModelToEntityMapper(Mapper<SlotModel, Slot> slotModelToEntityMapper,
                                      Mapper<CustomerModel, Customer> customerModelToEntityMapper) {
        this.slotModelToEntityMapper = slotModelToEntityMapper;
        this.customerModelToEntityMapper = customerModelToEntityMapper;
    }

    @Override
    public Booking transform(BookingModel from) {
        if (from == null) {
            return null;
        }
        Booking booking = new Booking();
        booking.setId(from.getId());
        booking.setActive(from.isActive());
        booking.setCarNumber(from.getCarNumber());
        booking.setCheckIn(from.getCheckIn());
        booking.setCheckOut(from.getCheckOut());
        booking.setSlot(slotModelToEntityMapper.transform(from.getSlot()));
        booking.setCustomer(customerModelToEntityMapper.transform(from.getCustomer()));
        return booking;
    }
}
