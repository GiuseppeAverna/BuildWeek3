package Team2.BuildWeek3.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.cglib.core.Local;

import java.time.LocalDate;

@Entity
@Table(name = "fattura")
@NoArgsConstructor
@Getter
@Setter
public class Fattura {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    LocalDate dataFattura;
    private long numeroFattura;
    private double importoFattura;
    private String statoFattura;
    @ManyToOne
    @JoinColumn(name = "cliente_id", referencedColumnName = "id")
    private Cliente cliente;


    public Fattura(LocalDate dataFattura, long numeroFattura, double importoFattura, String statoFattura, Cliente idCliente) {
        this.dataFattura = dataFattura;
        this.numeroFattura = numeroFattura;
        this.importoFattura = importoFattura;
        this.statoFattura = statoFattura;
        this.cliente = idCliente;
    }
}
