package Team2.BuildWeek3.services;

import Team2.BuildWeek3.entities.Cliente;
import Team2.BuildWeek3.entities.Comune;
import Team2.BuildWeek3.entities.Indirizzo;
import Team2.BuildWeek3.exception.BadRequestException;
import Team2.BuildWeek3.exception.NotFoundException;
import Team2.BuildWeek3.payloads.NewIndirizziDTO;
import Team2.BuildWeek3.repositories.ComuneDAO;
import Team2.BuildWeek3.repositories.IndirizzoDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.Optional;

public class IndirizziService {

    @Autowired
    private IndirizzoDAO indirizzoDAO;

    @Autowired
    private ComuneDAO comuneDAO;


    public Page<Indirizzo> getIndirizzo(int page, int size, String sort){
        if (size > 100) size = 100;
        Pageable pageable = PageRequest.of(page, size, Sort.by(sort));
        return indirizzoDAO.findAll(pageable);
    }

    public void deleteIndirizzo(long indirizziId) {
        Indirizzo indirizzo = findById(indirizziId);
        indirizzoDAO.delete(indirizzo);
    }



    public Indirizzo findById(long id) throws NotFoundException {
        Optional<Indirizzo> indirizzo = indirizzoDAO.findById((id));
        if (indirizzo.isEmpty()) {
            throw new NotFoundException(id);
        }
        return indirizzo.get();
    }
    }

