package Team2.BuildWeek3.payloads;

import Team2.BuildWeek3.entities.Indirizzo;
import Team2.BuildWeek3.entities.enums.TipoCliente;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
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
        @NotEmpty(message = "La data di inserimento è obbligatoria")
        LocalDate dataInserimento,
        @NotEmpty(message = "La data ultimo contatto è obbligatoria")
        LocalDate dataUltimoContatto,
        @Email
        String pec,
        @NotEmpty(message = "Un numero di telefono è obbligatorio")
        long telefono,
        @Email
        String emailContatto,

        String nomeContatto,

        String cognomeContatto,

        long telefonoContatto,
        String logoAziendale,
        @NotEmpty(message = "il tipo di cliente è obbligatorio")
        TipoCliente tipoCliente,
        @NotEmpty(message = "L'indirizzo della sede operativa è obbligatorio")
        Indirizzo sedeOperativa,
        @NotEmpty(message = "L'indirizzo della sede legale è obbligatorio")
        Indirizzo sedeLegale) {

}
