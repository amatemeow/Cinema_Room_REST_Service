package cinema.handlers;

import cinema.exceptions.CinemaInternalError;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class InternalServerErrorHandler {

    @ExceptionHandler(CinemaInternalError.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ErrorBean handleInternalServerErrorException(CinemaInternalError e) {
        ErrorBean errorBean = new ErrorBean();
        errorBean.setError("INTERNAL: " + e.getMessage());
        return errorBean;
    }
}

