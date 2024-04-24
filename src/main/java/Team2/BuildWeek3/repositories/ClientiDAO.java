package Team2.BuildWeek3.repositories;

import Team2.BuildWeek3.entities.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientiDAO extends JpaRepository<Cliente, Long> {
    Optional<Cliente> findByEmail(String email);
}
