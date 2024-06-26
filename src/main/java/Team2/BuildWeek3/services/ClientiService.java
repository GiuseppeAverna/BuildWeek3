package Team2.BuildWeek3.services;

import Team2.BuildWeek3.entities.Cliente;
import Team2.BuildWeek3.entities.Indirizzo;
import Team2.BuildWeek3.exception.BadRequestException;
import Team2.BuildWeek3.exception.NotFoundException;
import Team2.BuildWeek3.payloads.NewClientiDTO;
import Team2.BuildWeek3.payloads.NewIndirizziDTO;
import Team2.BuildWeek3.repositories.ClientiDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Service
public class ClientiService {

    @Autowired
    ClientiDAO clientiDAO;
    @Autowired
    IndirizziService indirizziService;


    public Cliente save(NewClientiDTO body) throws BadRequestException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        this.clientiDAO.findByEmail(body.email()).ifPresent(cliente -> {
            throw new BadRequestException("L'email è già registrata");
        });
        Cliente newCliente = new Cliente(body.ragioneSociale(), body.partitaIva(), body.email(), LocalDate.parse(body.dataInserimento(),formatter) , LocalDate.parse(body.dataUltimoContatto(),formatter) , body.pec(), body.telefono(), body.emailContatto(), body.nomeContatto(), body.cognomeContatto(), body.telefonoContatto(), body.logoAziendale(), body.getRoleEnum(), indirizziService.findById(body.sedeOperativa()), indirizziService.findById(body.sedeLegale()));
        return clientiDAO.save(newCliente);
    }

    public void deleteClienteById(long clienteId) {
        Cliente found = this.findById(clienteId);
        this.clientiDAO.delete(found);
    }

    public Cliente findById(Long clienteId) {
        return this.clientiDAO.findById(clienteId).orElseThrow(() -> new NotFoundException(clienteId));
    }

    public Cliente findByIdAndUpdateSedeLegale(Long clienteId, Long indirizzoId) {
        Cliente found = this.findById(clienteId);
        Indirizzo indirizzo = this.indirizziService.findById(indirizzoId);
        found.setSedeLegale(indirizzo);
        indirizzo.setSedeLegale(found);
        this.clientiDAO.save(found);
        this.indirizziService.save(new NewIndirizziDTO(indirizzo.getVia(), indirizzo.getCivico(), indirizzo.getLocalità(), indirizzo.getComune().getId(), indirizzo.getCap()));
        return found;
    }

    public Cliente findByIdAndUpdateSedeOperativa(Long clienteId, Long indirizzoId) {
        Cliente found = this.findById(clienteId);
        Indirizzo indirizzo = this.indirizziService.findById(indirizzoId);
        found.setSedeOperativa(indirizzo);
        indirizzo.setSedeOperativa(found);
        this.clientiDAO.save(found);
        this.indirizziService.save(new NewIndirizziDTO(indirizzo.getVia(), indirizzo.getCivico(), indirizzo.getLocalità(), indirizzo.getComune().getId(), indirizzo.getCap()));
        return found;
    }


    public Page<Cliente> getAllClienti(int page, int size, String sortBy) {
        if (size > 100) size = 100;
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return this.clientiDAO.findAll(pageable);
    }


}
