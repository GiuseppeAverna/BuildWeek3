package Team2.BuildWeek3.repositories;

import Team2.BuildWeek3.entities.Provincia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProvinciaDAO extends JpaRepository<Provincia,Long> {
//    Provincia findByNomeContainingIgnoreCase(String nome);

    Provincia findByNomeLikeIgnoreCase(String nome);
}
