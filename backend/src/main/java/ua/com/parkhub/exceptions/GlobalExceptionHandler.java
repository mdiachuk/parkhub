package ua.com.parkhub.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.logging.Level;
import java.util.logging.Logger;

@RestControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger LOGGER = Logger.getLogger(GlobalExceptionHandler.class.getName());

    @ExceptionHandler({BookingException.class,CustomerException.class,PermissionException.class})
    public ResponseEntity handleBookingCustomerPermissionException(ParkHubException e) {
        return ResponseEntity.badRequest().body(e.getStatusCode());
    }


    @ExceptionHandler(ParkingDoesntExistException.class)
    public ResponseEntity<Integer> handleParkingDoesntExistException(ParkingDoesntExistException e) {
        return ResponseEntity.badRequest().body(e.getStatusCode().getCode());
    }

    @ExceptionHandler({EmailException.class,ParkHubException.class,InvalidTokenException.class,PhoneNumberException.class, ExistingParkingException.class,AddressException.class,ParkingException.class,UserDoesntExistException.class})
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

    @ExceptionHandler({PasswordException.class})
    public ResponseEntity handlePasswordException(Exception e) {
        LOGGER.warning(e.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
    }

    @ExceptionHandler({Exception.class})
    public ResponseEntity handleUnexpectedExceptions(Exception e) {
        LOGGER.log(Level.WARNING, e.getMessage(), e);
        String message = "Sorry!Something went wrong on our server.";
        return ResponseEntity.status(500).body(message);
    }


}
