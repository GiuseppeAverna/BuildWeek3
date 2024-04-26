package Team2.BuildWeek3.controllers;


import Team2.BuildWeek3.entities.Fattura;
import Team2.BuildWeek3.services.QueryFatturaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/filter/fatture")
public class QueryFattureController {
    @Autowired
    QueryFatturaService queryFatturaService;
    @GetMapping("/cliente")
    @PreAuthorize("hasAuthority('ADMIN')")
    public List<Fattura> filtraPerIdCliente(@RequestParam(value = "idCliente") Long idCliente) {
        return queryFatturaService.filtraPerIdCliente(idCliente);
    }
    @GetMapping("/stato")
    @PreAuthorize("hasAuthority('ADMIN')")
    public List<Fattura> filtraPerStato(@RequestParam(value = "stato") String stato) {
        return queryFatturaService.filtraPerStato(stato);
    }
    @GetMapping("/intervallo")
    @PreAuthorize("hasAuthority('ADMIN')")
    public List<Fattura> filtraPerIntervalloDate(@RequestParam(value = "startDate") @DateTimeFormat(pattern = "dd-MM-yy") LocalDate startDate,
                                                 @RequestParam(value = "endDate") @DateTimeFormat(pattern = "dd-MM-yy") LocalDate endDate) {
        return queryFatturaService.filtraPerDataEmissione(startDate, endDate);
    }
    @GetMapping("/intervalloAnni")
    @PreAuthorize("hasAuthority('ADMIN')")
    public List<Fattura> filtraPerIntervalloAnni(@RequestParam(value = "annoA") int annoA,
                                                 @RequestParam(value = "annoB") int annoB) {
        return queryFatturaService.filtraPerIntervalloAnni(annoA, annoB);
    }

    @GetMapping("/range")
    @PreAuthorize("hasAuthority('ADMIN')")
    public List<Fattura> filtraPerRangeImporti(@RequestParam(value = "minImporto") double minImporto,
                                               @RequestParam(value = "maxImporto") double maxImporto) {
        return queryFatturaService.filtraPerRangeImporti(minImporto, maxImporto);
    }
}
