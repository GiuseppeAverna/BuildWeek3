package Team2.BuildWeek3.payloads;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;

public record NewIndirizziDTO(
    @NotEmpty(message = "La via è obbligatoria")
    String via,

    @PositiveOrZero(message = "Il numero civico deve essere un numero positivo o zero")
    int civico,

    @NotEmpty(message = "La località è obbligatoria")
     String localita,

    @PositiveOrZero(message = "Il CAP deve essere un numero positivo o zero")
    @Size(min = 5, max = 5, message = "Il CAP deve essere composto da 5 cifre")
    int cap

    ){
}
