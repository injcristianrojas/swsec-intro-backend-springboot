package cl.injcristianrojas.security.jwt;

import com.auth0.jwt.algorithms.Algorithm;

public class Constants {
	
	public static final String SECRET = "123456789012345678901234567890";
    public static final long EXPIRATION_TIME_IN_SECONDS = 86400; // seconds
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";
    public static final String LOGIN_URI = "/api/v2/login";
    
    public static Algorithm verificationAlgorithm() {
    	return Algorithm.HMAC256(SECRET);
    }

}

