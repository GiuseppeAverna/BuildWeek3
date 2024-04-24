package Team2.BuildWeek3.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class UnauthorizedException extends RuntimeException {

    public UnauthorizedException() {
        super("Utente non autorizzato.");
    }

    public UnauthorizedException(String message) {
        super(message);
    }
}
