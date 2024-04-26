package Team2.BuildWeek3.security;

import Team2.BuildWeek3.entities.Utente;
import Team2.BuildWeek3.exception.UnauthorizedException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JWTools {

    @Value("${jwt.secret}")
    private String secret;

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
                                        .getBytes()))
                .compact();
    }

    public void verifyToken(String token){
        try {
            Jwts.parser()
                    .verifyWith(Keys.hmacShaKeyFor(secret.getBytes()))
                    .build().parse(token);
        } catch (Exception ex) {
            throw new UnauthorizedException("Problemi col token! Per favore effettua di nuovo il login!");

        }
    }

    public String extractIdFromToken(String token){
        return Jwts.parser()
                .verifyWith(Keys.hmacShaKeyFor(secret.getBytes()))
                .build().parseSignedClaims(token).getPayload().getSubject();
    }
}