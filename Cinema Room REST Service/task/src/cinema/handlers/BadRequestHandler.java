package cinema.handlers;

import cinema.exceptions.CinemaBadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class BadRequestHandler {

    @ExceptionHandler(CinemaBadRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorBean handleBadRequestException(CinemaBadRequestException e) {
        ErrorBean errorBean = new ErrorBean();
        errorBean.setError(e.getMessage());
        return errorBean;
    }
}

