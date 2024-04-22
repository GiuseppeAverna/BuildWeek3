package Team2.BuildWeek3.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "indirizzo")
@NoArgsConstructor
@Getter
@Setter
public class Indirizzo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String via;
    int civico;
    private String località;
    private int cap;
    @OneToOne(mappedBy = "indirizzo")
    private Comune comune;
    @OneToOne(mappedBy = "sedeOperativa")
    private Cliente sedeOperativa;
    @OneToOne(mappedBy = "sedeLegale")
    private Cliente sedeLegale;

    public Indirizzo(String via, int civico, String località, int cap, Comune comune) {
        this.via = via;
        this.civico = civico;
        this.località = località;
        this.cap = cap;
        this.comune = comune;
    }
}
