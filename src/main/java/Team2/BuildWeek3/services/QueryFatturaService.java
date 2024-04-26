package Team2.BuildWeek3.services;

import Team2.BuildWeek3.entities.Fattura;
import Team2.BuildWeek3.repositories.FattureDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class QueryFatturaService {
    @Autowired
    FattureDAO fatturaDAO;

    public List<Fattura> filtraPerIdCliente(Long idCliente) {
        List<Fattura> fatture = this.fatturaDAO.findAll();
        return fatture.stream()
                .filter(fattura -> fattura.getCliente().getId() == idCliente)
                .collect(Collectors.toList());
    }

    public List<Fattura> filtraPerStato(String stato) {
        List<Fattura> fatture = this.fatturaDAO.findAll();
        return fatture.stream()
                .filter(fattura -> fattura.getStatoFattura().equalsIgnoreCase(stato))
                .collect(Collectors.toList());
    }

    public List<Fattura> filtraPerDataEmissione(LocalDate dataInizio, LocalDate dataFine) {
        List<Fattura> fatture = this.fatturaDAO.findAll();
        return fatture.stream()
                .filter(fattura -> fattura.getDataFattura().isAfter(dataInizio) &&
                        fattura.getDataFattura().isBefore(dataFine))
                .collect(Collectors.toList());
    }

    public List<Fattura> filtraPerIntervalloAnni(int annoA, int annoB) {
        List<Fattura> fatture = this.fatturaDAO.findAll();
        return fatture.stream()
                .filter(fattura -> {
                    int annoFattura = fattura.getDataFattura().getYear();
                    return annoFattura >= annoA && annoFattura <= annoB;
                })
                .collect(Collectors.toList());
    }

    public List<Fattura> filtraPerRangeImporti(double minImporto, double maxImporto) {
        List<Fattura> fatture = this.fatturaDAO.findAll();
        return fatture.stream()
                .filter(fattura -> fattura.getImportoFattura() >= minImporto &&
                        fattura.getImportoFattura() <= maxImporto)
                .collect(Collectors.toList());
    }
}
