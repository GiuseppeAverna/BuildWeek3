package Team2.BuildWeek3.services;

import Team2.BuildWeek3.entities.Indirizzo;
import Team2.BuildWeek3.repositories.IndirizzoDAO;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class IndirizziService {

    @Autowired
    private IndirizzoDAO indirizzoDAO;

    public List<Indirizzo> getAllIndirizzi() {
        return indirizzoDAO.findAll();
    }

    public Indirizzo getIndirizzoById(Long id) {
        return indirizzoDAO.findById(id).orElse(null);
    }

    public Indirizzo saveOrUpdateIndirizzo(Indirizzo indirizzo) {
        return indirizzoDAO.save(indirizzo);
    }

    public void deleteIndirizzo(Long id) {
        indirizzoDAO.deleteById(id);
    }
}
