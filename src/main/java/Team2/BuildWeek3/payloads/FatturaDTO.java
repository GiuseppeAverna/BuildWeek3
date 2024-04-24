package Team2.BuildWeek3.payloads;

import Team2.BuildWeek3.entities.Cliente;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;

public record FatturaDTO(
        @NotEmpty(message = "Il campo dataFattura non può essere vuoto")
        @Pattern(regexp = "^(?:31([/\\-.])(?:0?[13578]|1[02])\\1|(?:29|30)([/\\-.])(?:0?[13-9]|1[0-2])\\2)(?:1[6-9]|[2-9]\\d)?\\d{2}$|^29([/\\-.])0?2\\3(?:(?:1[6-9]|[2-9]\\d)?(?:0[48]|[2468][048]|[13579][26])|(?:16|[2468][048]|[3579][26])00)$|^(?:0?[1-9]|1\\d|2[0-8])([/\\-.])(?:0?[1-9]|1[0-2])\\4(?:1[6-9]|[2-9]\\d)?\\d{2}$",message = "Inserisci una data valida")
        String dataFattura,
        @NotEmpty(message = "Il campo numeroFattura non può essere vuoto")
        long numeroFattura,
        @NotEmpty(message = "Il campo importoFattura non può essere vuoto")
        double importoFattura,
        String statoFattura,
        Long clienteId
) {
}