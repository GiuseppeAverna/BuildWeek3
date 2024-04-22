package Team2.BuildWeek3.entities;


import Team2.BuildWeek3.entities.enums.TipoCliente;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.Date;

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
   private  LocalDate dataUltimoContatto;
    private double fattutratoAnnuale;
    private String pec;
    private long telefono;
    private String emailContatto;
    private String nomeContatto;
   private  String cognomeContatto;
    private long telefonoContatto;
    private String logoAziendale;
    private TipoCliente tipoCliente;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "indirizzo_id", referencedColumnName = "id")
    private Indirizzo sedeOperativa;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "indirizzo_id", referencedColumnName = "id")
    private Indirizzo sedeLegale;



    public Cliente(String ragioneSociale,
                   String partitaIva,
                   String email,
                   LocalDate dataInserimento,
                   LocalDate dataUltimoContatto,
                   double fattutratoAnnuale,
                   String pec, long telefono,
                   String emailContatto,
                   String nomeContatto,
                   String cognomeContatto,
                   long telefonoContatto,
                   String logoAziendale,
                   TipoCliente tipoCliente,
                   Indirizzo sedeLegale,
                   Indirizzo sedeOperativa) {
        this.ragioneSociale = ragioneSociale;
        this.partitaIva = partitaIva;
        this.email = email;
        this.dataInserimento = dataInserimento;
        this.dataUltimoContatto = dataUltimoContatto;
        this.fattutratoAnnuale = fattutratoAnnuale;
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
}
