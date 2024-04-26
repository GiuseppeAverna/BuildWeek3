package Team2.BuildWeek3.services;

import Team2.BuildWeek3.entities.Provincia;
import Team2.BuildWeek3.repositories.ProvinciaDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProvinciaProva {
    @Autowired
    ProvinciaDAO provinciaDAO;

    public void saveProvincia(Provincia provincia) {
        provinciaDAO.save(provincia);
        System.out.println(provincia);
    }

    public Provincia cercaProvinciaPerNome(String nome) {

        return provinciaDAO.findFirstByNomeLikeIgnoreCase(nome);
    }
}
