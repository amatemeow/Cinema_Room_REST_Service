package cinema.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class OutOfCinemaRangeException extends CinemaBadRequestException {
    public OutOfCinemaRangeException() {
        super("The number of a row or a column is out of bounds!");
    }
}
