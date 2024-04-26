package Team2.BuildWeek3.entities;


import Team2.BuildWeek3.entities.enums.TipoCliente;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;


@Entity
@Table(name = "clienti")
@NoArgsConstructor
@Getter
@Setter
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    long id;
    private String ragioneSociale;
    private String partitaIva;
    private String email;
    private LocalDate dataInserimento;
    private LocalDate dataUltimoContatto;
    private String pec;
    private String telefono;
    private String emailContatto;
    private String nomeContatto;
    private String cognomeContatto;
    private String telefonoContatto;
    private String logoAziendale;
    @Enumerated(EnumType.STRING)
    private TipoCliente tipoCliente;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "sedeOperativa_id")
    private Indirizzo sedeOperativa;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "sedeLegale_id")
    private Indirizzo sedeLegale;
    @OneToMany(mappedBy = "cliente")
    private List<Fattura> fatture;


    public Cliente(String ragioneSociale,
                   String partitaIva,
                   String email,
                   LocalDate dataInserimento,
                   LocalDate dataUltimoContatto,
                   String pec,
                   String telefono,
                   String emailContatto,
                   String nomeContatto,
                   String cognomeContatto,
                   String telefonoContatto,
                   String logoAziendale,
                   TipoCliente tipoCliente,
                   Indirizzo sedeLegale,
                   Indirizzo sedeOperativa) {
        this.ragioneSociale = ragioneSociale;
        this.partitaIva = partitaIva;
        this.email = email;
        this.dataInserimento = dataInserimento;
        this.dataUltimoContatto = dataUltimoContatto;
        this.pec = pec;
        this.telefono = telefono;
        this.emailContatto = emailContatto;
        this.nomeContatto = nomeContatto;
        this.cognomeContatto = cognomeContatto;
        this.telefonoContatto = telefonoContatto;
        this.logoAziendale = logoAziendale;
        this.tipoCliente = tipoCliente;
        this.sedeLegale = sedeLegale;
        this.sedeOperativa = sedeOperativa;
    }

    public void aggiungiFattura(Fattura fattura) {
        fatture.add(fattura);
    }

    public double getFatturato(int anno) {
        double totaleFatturato = 0.0;
        for (Fattura fattura : fatture) {
            Calendar calendar = new GregorianCalendar();
            calendar.setTime(fattura.getDataFattura());
            if (calendar.get(Calendar.YEAR) == anno) {
                totaleFatturato += fattura.getImportoFattura();
            }
        }
        return totaleFatturato;
    }
}
