package cinema.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class WrongPasswordException extends CinemaUnauthorizedException {
    public WrongPasswordException() {
        super("The password is wrong!");
    }
}
