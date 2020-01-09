package ua.com.parkhub.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;
import java.io.Serializable;

public class ParkingFormDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotEmpty(message="car number is mandatory")
    @Pattern(regexp ="[A-Z]{2}\\d{4}[A-Z]{2}", message="car number has incorrect format")
    private String carNumber;
    @NotEmpty(message="phone number is mandatory")
    @Pattern(regexp ="^\\+380\\d{9}$", message = "phone number has incorrect format")
    private String phoneNumber;
    @Positive
    private Long slotId;
}
