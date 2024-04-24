package Team2.BuildWeek3.security;

import Team2.BuildWeek3.entities.Utente;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JWTools {

    public String createToken(Utente utente){
        return Jwts.builder()
                .issuedAt(
                        new Date(
                                System
                                        .currentTimeMillis()))
                .expiration(
                        new Date(
                                System.currentTimeMillis()
                                        + 1000 * 60 * 60 * 24
                                        * 7))
                .subject(
                        String.valueOf(
                                utente.getId()))
                .signWith(
                        Keys.hmacShaKeyFor(
                                "cLWiN3LNt600YKWLgFO3AyWYOdjtAPNbGvgLIX5WPv5FfHvdAx"
                                        .getBytes())) // Firmo il token con algoritmo HMAC passandogli il SEGRETO
                .compact();
    }

    public void verifyToken(String token){}
}