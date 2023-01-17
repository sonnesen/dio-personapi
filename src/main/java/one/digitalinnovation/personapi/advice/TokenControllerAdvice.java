package one.digitalinnovation.personapi.advice;

import one.digitalinnovation.personapi.exceptions.RefreshTokenExpirationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDate;

@RestControllerAdvice
public class TokenControllerAdvice {

    @ExceptionHandler(value = RefreshTokenExpirationException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ErrorMessage handleRefreshTokenExpirationException(RefreshTokenExpirationException ex, WebRequest request) {
        return new ErrorMessage(
                HttpStatus.FORBIDDEN.value(),
                LocalDate.now(),
                ex.getMessage(),
                request.getDescription(false)
        );
    }
}
