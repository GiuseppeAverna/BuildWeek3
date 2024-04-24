package Team2.BuildWeek3.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class ForbiddenException extends RuntimeException {

    public ForbiddenException() {
        super("Azione non consentita.");
    }

    public ForbiddenException(String message) {
        super(message);
    }
}
