package Team2.BuildWeek3.entities;

import Team2.BuildWeek3.entities.enums.Role;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "utente")
@NoArgsConstructor
@Getter
@Setter
public class Utente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String username;
    private String nome;
    private String cognome;
    private String email;
    private String password;
    private Role ruolo;
    private String avatar;


    public Utente(String username, String nome, String cognome, String email, String password, Role ruolo, String avatar) {
        this.username = username;
        this.nome = nome;
        this.cognome = cognome;
        this.email = email;
        this.password = password;
        this.ruolo = ruolo;
        this.avatar = avatar;
    }


}

