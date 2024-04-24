package Team2.BuildWeek3.repositories;

import Team2.BuildWeek3.entities.Utente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UtentiDAO extends JpaRepository<Utente, Long> {
    Optional<Utente> findByEmail(String email);
}
