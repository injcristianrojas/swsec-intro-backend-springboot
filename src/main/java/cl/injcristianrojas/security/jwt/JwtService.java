package cl.injcristianrojas.security.jwt;

import java.util.Date;

import static cl.injcristianrojas.security.jwt.Constants.EXPIRATION_TIME_IN_SECONDS;
import static cl.injcristianrojas.security.jwt.Constants.TOKEN_PREFIX;
import static cl.injcristianrojas.security.jwt.Constants.verificationAlgorithm;


import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.auth0.jwt.JWT;

@Component
public class JwtService {

    public Object generateToken(Authentication auth) {
        MainUserPrincipal principal = (MainUserPrincipal) auth.getPrincipal();
        return JWT.create()
                .withSubject(principal.getUsername())
                .withClaim("role", principal.getMainRole())
                .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRATION_TIME_IN_SECONDS))
                .sign(verificationAlgorithm());
    }

    public String extractUserName(String token) {
        return JWT.require(verificationAlgorithm()).build()
        .verify(token.replace(TOKEN_PREFIX, ""))
        .getSubject();
    }

    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String userName = extractUserName(token);
        return (userName.equals(userDetails.getUsername())) && !isTokenExpired(token);
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return JWT.require(verificationAlgorithm()).build()
        .verify(token.replace(TOKEN_PREFIX, ""))
        .getExpiresAt();
    }

}
