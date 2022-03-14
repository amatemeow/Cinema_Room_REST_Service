package cinema.handlers;

import cinema.exceptions.CinemaUnauthorizedException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class UnauthorizedHandler {

    @ExceptionHandler(CinemaUnauthorizedException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ResponseBody
    public ErrorBean handleUnauthorizedException(CinemaUnauthorizedException e) {
        ErrorBean errorBean = new ErrorBean();
        errorBean.setError(e.getMessage());
        return errorBean;
    }
}

