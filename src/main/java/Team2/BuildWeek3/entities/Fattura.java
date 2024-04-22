package Team2.BuildWeek3.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
    private Cliente idCliente;


    public Fattura(LocalDate dataFattura, long numeroFattura, double importoFattura, String statoFattura, Cliente idCliente) {
        this.dataFattura = dataFattura;
        this.numeroFattura = numeroFattura;
        this.importoFattura = importoFattura;
        this.statoFattura = statoFattura;
        this.idCliente = idCliente;
    }
}
