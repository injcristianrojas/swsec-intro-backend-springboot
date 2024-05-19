package cl.injcristianrojas.security.jwt;

import io.jsonwebtoken.SignatureAlgorithm;

public class Constants {
	
	public static final String SECRET = "123456789012345678901234567890";
    public static final long EXPIRATION_TIME_IN_SECONDS = 86_400; // seconds
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";
    public static final String LOGIN_URI = "/api/v2/login";
    
    public static SignatureAlgorithm verificationAlgorithm() {
    	return SignatureAlgorithm.HS256;
    }

}
