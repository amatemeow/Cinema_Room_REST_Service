package cinema.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class AlreadyPurchasedException extends CinemaBadRequestException {
    public AlreadyPurchasedException() {
        super("The ticket has been already purchased!");
    }
}
