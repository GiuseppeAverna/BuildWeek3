package Team2.BuildWeek3.services;


import Team2.BuildWeek3.entities.Comune;
import Team2.BuildWeek3.entities.Indirizzo;
import Team2.BuildWeek3.exception.NotFoundException;
import Team2.BuildWeek3.payloads.NewIndirizziDTO;
import Team2.BuildWeek3.repositories.ComuneDAO;
import Team2.BuildWeek3.repositories.IndirizzoDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class IndirizziService {

    @Autowired
    private IndirizzoDAO indirizzoDAO;

    @Autowired
    private ComuneDAO comuneDAO;


    public Indirizzo save(NewIndirizziDTO body) {
        Indirizzo indirizzo = new Indirizzo(body.via(), body.civico(), body.localita(), body.cap(), comuneDAO.findById(body.comuneid()).orElseThrow(() -> new NotFoundException("comuneId non trovato")));
        Comune comune = comuneDAO.findById(body.comuneid()).orElseThrow(() -> new NotFoundException("comuneId non trovato"));
        indirizzoDAO.save(indirizzo);
        comune.setIndirizzo(indirizzo);
        comuneDAO.save(comune);
        return indirizzo;
    }

    public Page<Indirizzo> getIndirizzo(int page, int size, String sort) {
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
            throw new NotFoundException("Indirizzo con id: " + id + " non è stato trovato");
        }
        return indirizzo.get();
    }
}

