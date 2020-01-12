
package ua.com.parkhub.util.validator;

import org.modelmapper.PropertyMap;
import ua.com.parkhub.dto.BookingDTO;
import ua.com.parkhub.model.BookingModel;

public class BookingMap extends PropertyMap<BookingModel, BookingDTO> {

    @Override
    protected void configure() {

    }
}
