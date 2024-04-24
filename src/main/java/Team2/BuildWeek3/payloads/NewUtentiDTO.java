package Team2.BuildWeek3.payloads;

import Team2.BuildWeek3.entities.enums.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public record NewUtentiDTO(
        @NotEmpty(message = "L'username è obbligatorio")
        @Size(min = 3, max = 30, message = "L'username deve essere compreso tra i 3 e i 30 caratteri")
        String username,
        @NotEmpty(message = "Il nome proprio è obbligatorio")
        @Size(min = 3, max = 30, message = "Il nome proprio deve essere compreso tra i 3 e i 30 caratteri")
        String nome,
        @NotEmpty(message = "Il cognome è obbligatorio")
        @Size(min = 3, max = 30, message = "Il cognome deve essere compreso tra i 3 e i 30 caratteri")
        String cognome,
        @NotEmpty(message = "L'email è obbligatoria")
        @Email(message = "L'email inserita non è valida")
        String email,
        @NotEmpty(message = "La password è obbligatoria")
        @Size(min = 4, message = "La password deve avere come minimo 8 caratteri")
        String password,
        @NotEmpty(message = "L'avatar è obbligatorio")
        @Size(min = 4, message = "L'avatar deve avere come minimo 8 caratteri")
        String avatar,
        @NotEmpty(message = "Il ruolo è obbligatorio")
        @Size(min = 2, message = "Il ruolo deve avere minimo 2 caratteri")
        String ruolo) {

    public Role getRoleEnum() {
        return Role.valueOf(ruolo.toUpperCase());
    }

}
