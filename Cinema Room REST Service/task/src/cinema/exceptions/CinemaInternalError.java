package cinema.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class CinemaInternalError extends RuntimeException {
    public CinemaInternalError(String message) {
        super();
    }
}
