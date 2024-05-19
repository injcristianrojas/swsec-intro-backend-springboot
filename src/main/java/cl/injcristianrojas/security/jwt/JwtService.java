package cl.injcristianrojas.security.jwt;

import java.util.Date;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.auth0.jwt.JWT;

@Component
public class JwtService {

    public Object generateToken(Authentication auth) {
        MainUserPrincipal principal = (MainUserPrincipal) auth.getPrincipal();
        return JWT.create()
                .withSubject(principal.getUsername())
                .withClaim("role", principal.getMainRole())
                .withExpiresAt(new Date(System.currentTimeMillis() + Constants.EXPIRATION_TIME_IN_SECONDS))
                .sign(Constants.verificationAlgorithm());
    }

}
