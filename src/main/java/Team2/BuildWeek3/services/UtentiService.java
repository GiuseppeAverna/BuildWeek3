package Team2.BuildWeek3.services;

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
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class UtentiService {
    @Autowired
    private UtentiDAO utentiDAO;
    @Autowired
    PasswordEncoder passwordEncoder;

    public Page<Utente> getUsers(int page, int size, String sortBy) {
        if (size > 100) size = 100;
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return this.utentiDAO.findAll(pageable);
    }

    public Utente save(NewUtentiDTO body) {
        this.utentiDAO.findByEmail(body.email()).ifPresent(
                user -> {
                    throw new BadRequestException("L'email " + body.email() + " è già in uso!");
                }
        );
        Utente newUtente = new Utente(body.username(), body.nome(), body.cognome(), body.email(),passwordEncoder.encode(body.password()), body.getRoleEnum(),
                "https://ui-avatars.com/api/?name=" + body.nome() + "+" + body.cognome());

        return utentiDAO.save(newUtente);
    }

    public Utente findById(long userId) {
        return this.utentiDAO.findById(userId).orElseThrow(() -> new NotFoundException(userId));
    }

    public Utente findByIdAndUpdate(long userId, Utente modifiedUser) {
        Utente found = this.findById(userId);
        found.setUsername(modifiedUser.getUsername());
        found.setNome(modifiedUser.getNome());
        found.setCognome(modifiedUser.getCognome());
        found.setEmail(modifiedUser.getEmail());
        found.setPassword(modifiedUser.getPassword());
        found.setAvatar("https://ui-avatars.com/api/?name=" + modifiedUser.getNome() + "+" + modifiedUser.getCognome());
        found.setRuolo(modifiedUser.getRuolo());
        ;
        return this.utentiDAO.save(found);
    }

    public void findByIdAndDelete(long userId) {
        Utente found = this.findById(userId);
        this.utentiDAO.delete(found);
    }

    public Utente findByEmail(String email) {
        return this.utentiDAO.findByEmail(email)
                .orElseThrow(() -> new NotFoundException("Utente non trovato per email: " + email));
    }
}