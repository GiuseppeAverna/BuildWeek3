package Team2.BuildWeek3.services;

import java.io.IOException;
import java.util.UUID;

import Team2.BuildWeek3.entities.Utente;
import Team2.BuildWeek3.exception.BadRequestException;
import Team2.BuildWeek3.exception.NotFoundException;
import Team2.BuildWeek3.payloads.NewUtentiDTO;
import Team2.BuildWeek3.repositories.UtentiDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;



@Service
public class UtentiService {
    @Autowired
    private UtentiDAO utentiDAO;

    public Page<Utente> getUsers(int page, int size, String sortBy){
        if(size > 100) size = 100;
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return this.utentiDAO.findAll(pageable);
    }

    public Utente save(NewUtentiDTO body){
        this.utentiDAO.findByEmail(body.email()).ifPresent(
                user -> {
                    throw new BadRequestException("L'email " + user.getEmail() + " è già in uso!");
                }
        );
        Utente newUtente = new Utente(body.username(),body.nome(), body.cognome(), body.email(), body.password(),body.ruolo(),
                "https://ui-avatars.com/api/?name="+ body.nome() + "+" + body.cognome());

        return UtentiDAO.save(newUtente);
    }

    public Utente findById(UUID userId){
        return this.utentiDAO.findById(userId).orElseThrow(() -> new NotFoundException(userId));
    }

    public Utente findByIdAndUpdate(UUID userId, Utente modifiedUser){
        Utente found = this.findById(userId);
        found.setName(modifiedUser.getName());
        found.setSurname(modifiedUser.getSurname());
        found.setEmail(modifiedUser.getEmail());
        found.setPassword(modifiedUser.getPassword());
        found.setAvatarURL("https://ui-avatars.com/api/?name="+ modifiedUser.getName() + "+" + modifiedUser.getSurname());
        return this.utentiDAO.save(found);
    }

    public void findByIdAndDelete(UUID userId){
        Utente found = this.findById(userId);
        this.utentiDAO.delete(found);
    }

}