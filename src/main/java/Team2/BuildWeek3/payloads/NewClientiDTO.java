package Team2.BuildWeek3.payloads;

import Team2.BuildWeek3.entities.enums.TipoCliente;
import Team2.BuildWeek3.exception.BadRequestException;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record NewClientiDTO(
        @NotEmpty(message = "La ragione sociale è obbligatoria")
        @Size(min = 3, max = 20, message = "La ragione sociale deve essere compresa tra 3 e 20 caratteri")
        String ragioneSociale,
        @NotEmpty(message = "La partita Iva è obbligatoria")
        @Size(max = 11, message = "La partita Iva non può avere più di 11 cifre")
        String partitaIva,
        @Email
        @NotEmpty(message = "L'email è obbligatoria")
        String email,
        @NotNull(message = "La data di inserimento è obbligatoria")
        LocalDate dataInserimento,
        @NotNull(message = "La data ultimo contatto è obbligatoria")
        LocalDate dataUltimoContatto,
        @Email
        String pec,
        @NotEmpty(message = "Un numero di telefono è obbligatorio")
        @Size(min = 9, max = 10)
        String telefono,
        @Email
        String emailContatto,

        @NotEmpty(message = "Il nome del contatto è obbligatorio")
        String nomeContatto,
        @NotEmpty(message = "Il cognome del contatto è obbligatorio")
        String cognomeContatto,
        @NotEmpty(message = "Un numero di telefono è obbligatorio")
        @Size(min = 9, max = 10)
        String telefonoContatto,
        String logoAziendale,
        @NotEmpty(message = "il tipo di cliente è obbligatorio")
        String tipoCliente,
        @NotNull(message = "L'indirizzo della sede operativa è obbligatorio")
        long sedeOperativa,
        @NotNull(message = "L'indirizzo della sede legale è obbligatorio")
        long sedeLegale) {
    public TipoCliente getRoleEnum() {
        String tipoClienteUpperCase = tipoCliente.toUpperCase();
        if ("SPA".equals(tipoClienteUpperCase) || "PA".equals(tipoClienteUpperCase) || "SAS".equals(tipoClienteUpperCase) || "SRL".equals(tipoClienteUpperCase)) {
            return TipoCliente.valueOf(tipoClienteUpperCase);
        } else {
            throw new BadRequestException("Tipo cliente non valido: " + tipoCliente);
        }

    }
}
