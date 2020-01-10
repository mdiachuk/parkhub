package ua.com.parkhub.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus. INTERNAL_SERVER_ERROR, reason = "This email is used")
public class ExistingUserExeption extends RuntimeException {
}
