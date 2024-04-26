package Team2.BuildWeek3.services;


import Team2.BuildWeek3.entities.Cliente;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;


public class ClienteService {
    private List<Cliente> clienti;

    public ClienteService(List<Cliente> clienti) {
        this.clienti = clienti;
    }

    public List<Cliente> filtraPerDataInserimento(LocalDate dataInizio, LocalDate dataFine) {
        return clienti.stream()
                .filter(cliente -> cliente.getDataInserimento().isAfter(dataInizio) &&
                        cliente.getDataInserimento().isBefore(dataFine))
                .collect(Collectors.toList());
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

    public List<Cliente> filtraPerNome(String parteNome) {
        return clienti.stream()
                .filter(cliente -> cliente.getRagioneSociale().toLowerCase().contains(parteNome.toLowerCase()))
                .collect(Collectors.toList());
    }
}