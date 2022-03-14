package cinema.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class CinemaUnauthorizedException extends RuntimeException {
    public CinemaUnauthorizedException(String message) {
        super(message);
    }
}
