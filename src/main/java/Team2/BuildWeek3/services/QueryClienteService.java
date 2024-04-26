package Team2.BuildWeek3.services;


import Team2.BuildWeek3.entities.Cliente;
import Team2.BuildWeek3.repositories.ClientiDAO;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;


public class QueryClienteService {
    private List<Cliente> clienti;

    public QueryClienteService(List<Cliente> clienti) {
        this.clienti = clienti;
    }

    public Page<Customer> filtraPerDataInserimento(LocalDate dataInizio, LocalDate dataFine, Pageable pageable) {
        return ClientiDAO.findAllByDataInserimentoBetween(dataInizio, dataFine, pageable);
    }

    public List<Cliente> filtraPerDataContatto(LocalDate dataInizio, LocalDate dataFine) {
        return clienti.stream()
                .filter(cliente -> cliente.getDataUltimoContatto().isAfter(dataInizio) &&
                        cliente.getDataUltimoContatto().isBefore(dataFine))
                .collect(Collectors.toList());
    }

    public List<Cliente> filtraPerFatturato(double minFatturato, double maxFatturato) {
        return clienti.stream()
                .filter(cliente -> cliente.getFatturato() >= minFatturato &&
                        cliente.getFatturato() <= maxFatturato)
                .collect(Collectors.toList());
    }

    public List<Cliente> filtraPerNomeContatto(String parteNome) {
        return clienti.stream()
                .filter(cliente -> cliente.getNomeContatto().toLowerCase().contains(parteNome.toLowerCase()))
                .collect(Collectors.toList());
    }
}