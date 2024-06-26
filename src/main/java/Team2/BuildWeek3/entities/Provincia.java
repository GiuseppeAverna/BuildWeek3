package Team2.BuildWeek3.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Entity
@Table(name = "provincia")
@NoArgsConstructor
@Getter
@Setter

public class Provincia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
     private String nome;
    @OneToMany(mappedBy = "provincia", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<Comune> comuni;


    public Provincia(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        return "Provincia{" +
                "nome='" + nome + '\'' +
                '}';
    }
}
