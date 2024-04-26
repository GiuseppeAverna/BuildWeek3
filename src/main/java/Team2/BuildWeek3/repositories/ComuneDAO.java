package Team2.BuildWeek3.repositories;

import Team2.BuildWeek3.entities.Comune;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ComuneDAO extends JpaRepository<Comune, Long> {
    Optional<Comune> findByNome(String nome);

    Comune findByNomeAndProvinciaNome(String nome, String provincia);


}
