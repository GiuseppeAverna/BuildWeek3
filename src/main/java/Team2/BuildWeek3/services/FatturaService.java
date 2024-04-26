package Team2.BuildWeek3.services;

import Team2.BuildWeek3.entities.Fattura;
import Team2.BuildWeek3.exception.NotFoundException;
import Team2.BuildWeek3.payloads.FatturaDTO;
import Team2.BuildWeek3.payloads.FatturaRespDTO;
import Team2.BuildWeek3.payloads.StatoFatturaDTO;
import Team2.BuildWeek3.repositories.FattureDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Service
public class FatturaService {
    @Autowired
    private FattureDAO fatturaDAO;
    @Autowired
    private ClientiService clientiService;

    public FatturaRespDTO save (FatturaDTO body){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        fatturaDAO.save(new Fattura(LocalDate.parse( body.dataFattura(),formatter),body.numeroFattura(), body.importoFattura() ,body.statoFattura(),clientiService.findById( body.clienteId())));

        return new FatturaRespDTO(body.dataFattura(), body.numeroFattura(), body.importoFattura(), body.statoFattura(), clientiService.findById(body.clienteId()));
    }

    public Fattura findByNumero(long numeroFattura) {
        return fatturaDAO.findByNumeroFattura(numeroFattura);
    }

    public Page<Fattura> findByCliente(long clienteId,int page, int size, String sort) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sort));
        Page<Fattura> fatture = fatturaDAO.findByCliente(clienteId, pageable);
        return fatture;
    }


    public FatturaRespDTO findAndUpdate(long numeroFattura, StatoFatturaDTO body) {
        Fattura fattura = fatturaDAO.findByNumeroFattura(numeroFattura);

        if (fattura == null) {
            throw new NotFoundException("Fattura non trovata");
        }else{
            fattura.setStatoFattura(body.statoFattura());
            return new FatturaRespDTO( fattura.getDataFattura().toString(), fattura.getNumeroFattura(), fattura.getImportoFattura(), fattura.getStatoFattura(), fattura.getCliente());
        }
    }

    public void delete(long numeroFattura) {
        Fattura fattura = fatturaDAO.findByNumeroFattura(numeroFattura);
        if (fattura == null) {
            throw new NotFoundException("Fattura non trovata");
        } else {
            fatturaDAO.delete(fattura);
        }
    }

}
