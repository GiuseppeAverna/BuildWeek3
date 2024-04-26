package Team2.BuildWeek3.services;


import Team2.BuildWeek3.entities.Cliente;
import Team2.BuildWeek3.repositories.ClientiDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class QueryClienteService {
    @Autowired
    ClientiDAO clienteDAO;


    public List<Cliente> filtraPerDataInserimento(LocalDate dataInizio, LocalDate dataFine) {
        List<Cliente> clienti = this.clienteDAO.findAll();
        return clienti.stream()
                .filter(cliente -> cliente.getDataInserimento().isAfter(dataInizio) &&
                        cliente.getDataInserimento().isBefore(dataFine))
                .collect(Collectors.toList());
    }

    public List<Cliente> filtraPerDataContatto(LocalDate dataInizio, LocalDate dataFine) {
        List<Cliente> clienti = this.clienteDAO.findAll();
        return clienti.stream()
                .filter(cliente -> cliente.getDataUltimoContatto().isAfter(dataInizio) &&
                        cliente.getDataUltimoContatto().isBefore(dataFine))
                .collect(Collectors.toList());
    }

    public List<Cliente> filtraPerFatturato(double minFatturato, double maxFatturato,int anno) {
        List<Cliente> clienti = this.clienteDAO.findAll();
        return clienti.stream()
                .filter(cliente -> cliente.getFatturato(anno) >= minFatturato &&
                        cliente.getFatturato(anno) <= maxFatturato)
                .collect(Collectors.toList());
    }

    public List<Cliente> filtraPerNomeContatto(String parteNome) {
        List<Cliente> clienti = this.clienteDAO.findAll();
        return clienti.stream()
                .filter(cliente -> cliente.getNomeContatto().toLowerCase().contains(parteNome.toLowerCase()))
                .collect(Collectors.toList());
    }
}