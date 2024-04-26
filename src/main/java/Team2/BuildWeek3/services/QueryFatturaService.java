package Team2.BuildWeek3.services;

import Team2.BuildWeek3.entities.Fattura;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class QueryFatturaService {
    private List<Fattura> fatture;

    public QueryFatturaService(List<Fattura> fatture) {
        this.fatture = fatture;
    }

    public List<Fattura> filtraPerIdCliente(String idCliente) {
        return fatture.stream()
                .filter(fattura -> fattura.getIdCliente().equals(idCliente))
                .collect(Collectors.toList());
    }

    public List<Fattura> filtraPerStato(String stato) {
        return fatture.stream()
                .filter(fattura -> fattura.getStatoFattura().equalsIgnoreCase(stato))
                .collect(Collectors.toList());
    }

    public List<Fattura> filtraPerDataEmissione(LocalDate dataInizio, LocalDate dataFine) {
        return fatture.stream()
                .filter(fattura -> fattura.getDataFattura().isAfter(dataInizio) &&
                        fattura.getDataFattura().isBefore(dataFine))
                .collect(Collectors.toList());
    }

    public List<Fattura> filtraPerIntervalloAnni(int annoA, int annoB) {
        return fatture.stream()
                .filter(fattura -> {
                    int annoFattura = fattura.getDataFattura().getYear();
                    return annoFattura >= annoA && annoFattura <= annoB;
                })
                .collect(Collectors.toList());
    }

    public List<Fattura> filtraPerRangeImporti(double minImporto, double maxImporto) {
        return fatture.stream()
                .filter(fattura -> fattura.getImportoFattura() >= minImporto &&
                        fattura.getImportoFattura() <= maxImporto)
                .collect(Collectors.toList());
    }
}
