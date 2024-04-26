package Team2.BuildWeek3.controllers;


import Team2.BuildWeek3.entities.Cliente;
import Team2.BuildWeek3.services.QueryClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
@RequestMapping("/filter/clienti")
public class QueryClientiController {
    @Autowired
    QueryClienteService queryFattureService;
    @GetMapping("/date")
    @PreAuthorize("hasAuthority('ADMIN')")
    public List<Cliente> filterByDate(@RequestParam(value = "startDate") @DateTimeFormat(pattern = "dd-MM-yy") String startDate,
                                      @RequestParam(value = "endDate") @DateTimeFormat(pattern = "dd-MM-yy") String endDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        return queryFattureService.filtraPerDataInserimento( LocalDate.parse(startDate,formatter),  LocalDate.parse(endDate,formatter));
    }
    @GetMapping("/dataContatto")
    @PreAuthorize("hasAuthority('ADMIN')")
    public List<Cliente> filterByDateContact(@RequestParam(value = "startDate") @DateTimeFormat(pattern = "dd-MM-yy") String startDate,
                                                @RequestParam(value = "endDate") @DateTimeFormat(pattern = "dd-MM-yy") String endDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        return queryFattureService.filtraPerDataContatto( LocalDate.parse(startDate,formatter),  LocalDate.parse(endDate,formatter));
    }
    @GetMapping("/fatturato")
    @PreAuthorize("hasAuthority('ADMIN')")
    public List<Cliente> filterByFatturato(@RequestParam(value =  "minFatturato") double minFatturato,
                                           @RequestParam(value = "maxFatturato") double maxFatturato,
                                           @RequestParam(value = "anno") int anno){
        return queryFattureService.filtraPerFatturato(minFatturato, maxFatturato, anno);
    }
    @GetMapping("/nomeContatto")
    @PreAuthorize("hasAuthority('ADMIN')")
    public List<Cliente> filterByNameContact(@RequestParam(value = "parteNome") String parteNome) {
        return queryFattureService.filtraPerNomeContatto(parteNome);
    }
}
