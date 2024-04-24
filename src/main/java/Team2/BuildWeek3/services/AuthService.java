package Team2.BuildWeek3.services;

import Team2.BuildWeek3.entities.Utente;
import Team2.BuildWeek3.exception.UnauthorizedException;
import Team2.BuildWeek3.payloads.UserLoginDTO;
import Team2.BuildWeek3.security.JWTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    @Autowired
    private UtentiService usersService;
    @Autowired
    private JWTools jwtTools;

    public String authenticateUserAndGenerateToken(UserLoginDTO payload){

        Utente utente = this.usersService.findByEmail(payload.email());
        if(utente.getPassword().equals(payload.password())) {
            return jwtTools.createToken(utente);
        } else {

            throw new UnauthorizedException("Credenziali non valide! Effettua di nuovo il login!");
        }


    }
}