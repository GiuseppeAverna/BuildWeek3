package Team2.BuildWeek3.payloads;

import Team2.BuildWeek3.entities.Cliente;

public record FatturaRespDTO(
        String dataFattura,
        long numeroFattura,
        double importoFattura,
        String statoFattura,
        Cliente cliente
) {
}
