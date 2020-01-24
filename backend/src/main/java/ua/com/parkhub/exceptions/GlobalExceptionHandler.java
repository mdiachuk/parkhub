package ua.com.parkhub.exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler({BookingException.class,CustomerException.class,PermissionException.class,UserDoesntExistException.class})
    public ResponseEntity handleBookingCustomerPermissionException(ParkHubException e) {
        return ResponseEntity.badRequest().body(e.getStatusCode());
    }


    @ExceptionHandler(ParkingDoesntExistException.class)
    public ResponseEntity<Integer> handleParkingDoesntExistException(ParkingDoesntExistException e) {
        return ResponseEntity.badRequest().body(e.getStatusCode().getCode());
    }

    @ExceptionHandler({EmailException.class,ParkHubException.class,InvalidTokenException.class,PhoneNumberException.class, ExistingParkingException.class,AddressException.class,ParkingException.class})
    public ResponseEntity handleEmailParkHubInvalidTokenException(RuntimeException e) {
        String message = e.getMessage();
        LOGGER.info(message);
        return ResponseEntity.badRequest().body(message);
    }

    @ExceptionHandler({NotFoundInDataBaseException.class,IllegalArgumentException.class})
    public ResponseEntity handleNotFoundInDataBaseIllegalArgumentException(RuntimeException e) {
        LOGGER.info(e.getMessage());
        String message = "Something went wrong on our server. Please, try again later.";
        return ResponseEntity.status(500).body(message);
    }

    @ExceptionHandler({Exception.class})
    public ResponseEntity handleUnexpectedExceptions(Exception e) {
        LOGGER.info(e.getMessage());
        String message = "Sorry!Something went wrong on our server.";
        return ResponseEntity.status(500).body(message);
    }

}
