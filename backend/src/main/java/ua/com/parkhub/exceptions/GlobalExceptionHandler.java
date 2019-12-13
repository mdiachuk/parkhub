package ua.com.parkhub.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class GlobalExceptionHandler {

    @ExceptionHandler(ParkHubException.class)
    public void handleParameterException(Throwable throwable, HttpServletResponse response)
            throws IOException {
        //TODO logger
        response.sendError(HttpStatus.BAD_REQUEST.value());
    }
}
