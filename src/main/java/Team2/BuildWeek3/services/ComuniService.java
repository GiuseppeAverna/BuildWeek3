package Team2.BuildWeek3.services;

import Team2.BuildWeek3.entities.Comune;
import Team2.BuildWeek3.repositories.ComuneDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ComuniService {
    @Autowired
    private ComuneDAO comuneDAO;

    public List<Comune> getAllComuni() {
        return comuneDAO.findAll();
    }

    public Comune getComuneById(Long id) {
        return comuneDAO.findById(id).orElse(null);
    }

    public Comune saveOrUpdateComune(Comune comune) {
        return comuneDAO.save(comune);
    }

    public void deleteComune(Long id) {
        comuneDAO.deleteById(id);
    }
}


