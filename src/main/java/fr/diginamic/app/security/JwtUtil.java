package fr.diginamic.app.security;

import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class JwtUtil {

    /** secretKey */
    @Value("${jwt.secret}")
    private String secretKey;

    /** expireIn */
    @Value("${jwt.expires_in}")
    private long expireIn;

    /**
     * Génère un token JWT Contenant
     * @param username email de l'utilisateur
     * @return String
     */
    public String generateToken(String username) {
        SecretKey key = Keys.hmacShaKeyFor(secretKey.getBytes()); // déjà typé pour HMAC

        return Jwts.builder()
                .subject(username)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + 1000L * expireIn))
                .signWith(key, Jwts.SIG.HS256)
                .compact();
    }

    /** Extrait le username du token
     * @param token token JWT
     * @return String
     */
    public String extractUsername(String token) {
        JwtParser parser = Jwts.parser()
                .verifyWith(Keys.hmacShaKeyFor(secretKey.getBytes()))
                .build();

        return parser.parseSignedClaims(token).getPayload().getSubject();
    }

    /**
     * Retourne si oui ou non le token JWT est valide. Il doit contenir le nom de
     * l'utilisateur authentifié et ne doit pas avoir expiré.
     *
     * @param token token
     * @param userDetails infos concernant l'utilisateur authentifié
     * @return boolean
     */
    public boolean validateToken(String token, UserDetails userDetails) {
        String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    /**
     * Contrôle si le JWT token a expiré ou non
     *
     * @param token token JWT
     * @return boolean
     */
    private boolean isTokenExpired(String token) {
        SecretKey key = Keys.hmacShaKeyFor(secretKey.getBytes());

        Date expiration = Jwts.parser()
                .verifyWith(key)
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getExpiration();

        return expiration.before(new Date());
    }
}