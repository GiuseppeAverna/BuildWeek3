package Team2.BuildWeek3.exception;

import lombok.Getter;

@Getter
public class NotFoundException extends RuntimeException {
    public NotFoundException(Long id){
        super("Il record con id: " + id + " non è stato trovato!");
    }
}
