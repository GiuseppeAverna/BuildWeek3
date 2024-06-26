package Team2.BuildWeek3.repositories;

import Team2.BuildWeek3.entities.Indirizzo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IndirizzoDAO extends JpaRepository<Indirizzo, Long> {
    Optional<Indirizzo> findByVia(String via);
}
