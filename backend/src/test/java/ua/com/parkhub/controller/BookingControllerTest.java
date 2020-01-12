
//package ua.com.parkhub.controller;
//
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.springframework.test.web.servlet.MockMvc;
//
//import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
//import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//import ua.com.parkhub.dto.BookingDTO;
//import ua.com.parkhub.dto.BookingFormDTO;
//import ua.com.parkhub.exceptions.AddBookingException;
//import ua.com.parkhub.exceptions.ParkingNotFoundException;
//import ua.com.parkhub.model.Booking;
//import ua.com.parkhub.model.Customer;
//import ua.com.parkhub.model.Slot;
//
//@SpringBootTest
//@AutoConfigureMockMvc
//@RunWith(SpringRunner.class)
//public class BookingControllerTest {
//    private static Long slotId = 1L;
//    private static Long parkingId = 4L;
//    private static String carNumber = "AA767M3";
//    private static String phoneNumber = "+381551234567";
//    private static final String BOOKING_ROUTE = "/booking";
//    private static final String PARKING_ROUTE = "/parkings/".concat(parkingId.toString());
//
//    private BookingFormDTO bookingFormDTO;
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @Autowired
//    private BookingController bookingController;
//
//    public BookingControllerTest() {
//    }
//
//    @Before
//    public void init() {
//        bookingFormDTO = new BookingFormDTO();
//       bookingFormDTO.setSlotId(slotId);
//       bookingFormDTO.setCarNumber(carNumber);
//       bookingFormDTO.setPhoneNumber(phoneNumber);
//    }
//
//    @Test
//    public void create_shouldReturnBadRequestDueToAddBookingException() throws Exception {
//        this.mockMvc.perform(post(BOOKING_ROUTE))
//                .andDo(print())
//                .andExpect(status().isBadRequest());
//    }
//
//    @Test
//    public void create_shouldThrowAddBookingException() {
//        assertThatExceptionOfType(AddBookingException.class)
//                .isThrownBy(() -> bookingController.addBooking(bookingFormDTO));
//    }
//
//    @Test
//    public void get_shouldReturnNotFoundStatusDueToParkingNotFoundException() throws Exception {
//        this.mockMvc.perform(get(PARKING_ROUTE + "/" + parkingId))
//                .andDo(print())
//                .andExpect(status().isNotFound());
//    }
//
//    @Test
//    public void get_shouldThrowParkingNotFoundException() {
//        assertThatExceptionOfType(ParkingNotFoundException.class).isThrownBy(() -> bookingController.displayParking(parkingId));
//    }

//}
