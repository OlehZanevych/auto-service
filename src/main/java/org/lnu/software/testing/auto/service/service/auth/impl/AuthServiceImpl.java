package org.lnu.software.testing.auto.service.service.auth.impl;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.lnu.software.testing.auto.service.dto.user.credentials.UserCredentials;
import org.lnu.software.testing.auto.service.service.auth.AuthService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.util.Date;

@Service
public class AuthServiceImpl implements AuthService {

    private static final String AUTH_TOKEN_HEADER = "Authorization";
    private static final String USER_DATA_CLAIMS = "userData";
    private static final String AUTH_ISSUER = "Auto Service Auth";

    private final HttpServletResponse httpServletResponse;
    private final long authTokenLifetime;
    private final String jwtSigningKey;

    public AuthServiceImpl(HttpServletResponse httpServletResponse,
                           @Value("${AUTH_TOKEN_LIFETIME}") long authTokenLifetime,
                           @Value("${JWT_SIGNING_KEY}") String jwtSigningKey) {
        this.httpServletResponse = httpServletResponse;
        this.authTokenLifetime = 1000 * authTokenLifetime;
        this.jwtSigningKey = jwtSigningKey;
    }

    record UserData(
            String username,
            boolean isAdmin,
            String firstName,
            String middleName,
            String lastName
    ) {
    }

    public void login(UserCredentials userCredentials) {
        String authToken = generateToken(userCredentials);
        httpServletResponse.setHeader(AUTH_TOKEN_HEADER, authToken);
    }

    private String generateToken(UserCredentials userCredentials) {
        // TODO Home task: add call to DB to get UserData
        // Throw 404 if user not present
        UserData userData = new UserData("tomboy47", true, "Vasyl", "Ihorovych", "Troian");

        Claims claims = Jwts.claims();
        claims.put(USER_DATA_CLAIMS, userData);

        long creationTime = System.currentTimeMillis();
        long expirationTime = creationTime + authTokenLifetime;

        return Jwts.builder()
                .setClaims(claims)
                .setIssuer(AUTH_ISSUER)
                .setIssuedAt(new Date(creationTime))
                .setExpiration(new Date(expirationTime))
                .signWith(SignatureAlgorithm.HS256, jwtSigningKey)
                .compact();
    }
}
