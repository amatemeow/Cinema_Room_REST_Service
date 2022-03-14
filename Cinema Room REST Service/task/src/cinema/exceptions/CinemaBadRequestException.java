package cinema.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class CinemaBadRequestException extends RuntimeException {
    public CinemaBadRequestException(String message) {
        super(message);
    }
}
