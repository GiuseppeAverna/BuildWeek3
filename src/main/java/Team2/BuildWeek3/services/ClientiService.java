package Team2.BuildWeek3.services;

import Team2.BuildWeek3.entities.Cliente;
import Team2.BuildWeek3.repositories.ClientiDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientiService {

    @Autowired
    ClientiDAO clientiDAO;


    public Optional<Cliente> getClienteById(long id) {
        return clientiDAO.findById(id);
    }

    public Cliente save(Cliente cliente) {
        return clientiDAO.save(cliente);
    }

    public void deleteClienteById(long id) {
        clientiDAO.deleteById(id);
    }

    public List<Cliente> getAllClienti() {
        return clientiDAO.findAll();
    }
}
