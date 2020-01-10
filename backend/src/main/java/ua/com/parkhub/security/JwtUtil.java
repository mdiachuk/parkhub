package ua.com.parkhub.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import org.apache.commons.lang3.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private static Logger logger = LoggerFactory.getLogger(JwtUtil.class.getSimpleName());

    public String generateToken(String email, String role, Long id) {

        logger.debug(String.format("Generating token for %s with role %s", email, role));

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
        logger.debug(String.format("Adding authorities %s for %s", authorities.toString(), claims.getSubject()));


        return new UsernamePasswordAuthenticationToken(claims.getSubject(), null, authorities);
    }

    public Claims getClaims(String token) throws InvalidTokenException {
        try {
            logger.debug("Parsing JWT");
            return Jwts.parser()
                    .setSigningKey(jwtSecret)
                    .parseClaimsJws(token)
                    .getBody();

        } catch (JwtException | IllegalArgumentException e) {
            logger.error(e.getMessage(), e);
            throw new InvalidTokenException();
        }
    }

    public String refresh(String oldToken) throws InvalidTokenException {

        logger.debug("Refreshing JWT");
        Claims oldClaims = getClaims(oldToken);

        Date expiration = oldClaims.getExpiration();
        logger.debug("JWT expiration date: " + expiration);

        Date refreshingStart = DateUtils.addMinutes(expiration, -jwtRefreshingMinutes);
        logger.debug("Refreshing start date for current JWT: " + refreshingStart);

        return  (new Date().after(refreshingStart))
                ? generateToken(oldClaims.getSubject(), oldClaims.get("role", String.class), oldClaims.get("id", Long.class))
                : oldToken;
    }

}
