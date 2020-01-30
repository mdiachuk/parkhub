package ua.com.parkhub.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;
import ua.com.parkhub.exceptions.InvalidTokenException;
import ua.com.parkhub.values.Constants;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

@Component
public class JwtUtil {

    @Value("${jwt.secret}")
    private String jwtSecret;

    @Value("${jwt.life_minutes}")
    private int jwtLifeMinutes;

    @Value("${jwt.refreshing_minutes}")
    private int jwtRefreshingMinutes;

    private final int MILLISECONDS_IN_SECOND = 1000;
    private final int SECONDS_IN_MINUTE = 60;

    private static Logger logger = Logger.getLogger(JwtUtil.class.getName());

    public String generateToken(String email, String role, Long id) {

        logger.fine(String.format("Generating token for %s with role %s", email, role));

        return Jwts.builder()
                .setSubject(email)
                .claim("role", role)
                .claim("id", id)
                .setExpiration(generateExpDate())
                .signWith(Constants.JWT_SIGNATURE_ALGORITHM, jwtSecret)
                .compact();
    }

    private Date generateExpDate() {
        return new Date(System.currentTimeMillis() + jwtLifeMinutes * SECONDS_IN_MINUTE * MILLISECONDS_IN_SECOND);
    }

    public Authentication getAuthentication(String token) throws InvalidTokenException {

        Claims claims = getClaims(token);

        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_" + claims.get("role", String.class)));
        logger.fine(String.format("Adding authorities %s for %s", authorities.toString(), claims.getSubject()));


        return new UsernamePasswordAuthenticationToken(claims.getSubject(), null, authorities);
    }

    public Claims getClaims(String token) throws InvalidTokenException {
        try {
            logger.fine("Parsing JWT");
            return Jwts.parser()
                    .setSigningKey(jwtSecret)
                    .parseClaimsJws(token)
                    .getBody();

        } catch (JwtException | IllegalArgumentException e) {
            logger.warning(e.getMessage());
            throw new InvalidTokenException();
        }
    }

    public String refresh(String oldToken) throws InvalidTokenException {

        logger.fine("Refreshing JWT");
        Claims oldClaims = getClaims(oldToken);

        Date expiration = oldClaims.getExpiration();
        logger.fine("JWT expiration date: " + expiration);

        Date refreshingStart = DateUtils.addMinutes(expiration, -jwtRefreshingMinutes);
        logger.fine("Refreshing start date for current JWT: " + refreshingStart);

        return  (new Date().after(refreshingStart))
                ? generateToken(oldClaims.getSubject(), oldClaims.get("role", String.class), oldClaims.get("id", Long.class))
                : oldToken;
    }

}
