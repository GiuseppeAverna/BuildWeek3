package Team2.BuildWeek3.repositories;

import Team2.BuildWeek3.entities.Fattura;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FattureDAO extends JpaRepository<Fattura, Long> {

}
