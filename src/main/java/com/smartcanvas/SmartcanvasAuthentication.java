package com.smartcanvas;

import java.security.Key;

import org.jose4j.jws.AlgorithmIdentifiers;
import org.jose4j.jws.JsonWebSignature;
import org.jose4j.jwt.JwtClaims;
import org.jose4j.keys.HmacKey;
import org.jose4j.lang.JoseException;

import com.google.api.client.http.HttpExecuteInterceptor;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.util.Preconditions;

public class SmartcanvasAuthentication implements HttpRequestInitializer, HttpExecuteInterceptor {

    static final String X_CLIENT_ID = "x-client-id";
    static final String X_ACCESS_TOKEN = "x-access-token";
    
    private final String clientId;
    private final String clientSecret;
    private String accessToken;
    
    public SmartcanvasAuthentication(String clientId, String clientSecret) throws JoseException {
        super();
        this.clientId = Preconditions.checkNotNull(clientId, "Required parameter clientId must be specified.");
        this.clientSecret = Preconditions.checkNotNull(clientSecret, "Required parameter clientSecret must be specified.");
        this.accessToken = generateAccessToken();
    }

    @Override
    public void intercept(HttpRequest request) {
        request.setInterceptor(this);
    }

    @Override
    public void initialize(HttpRequest request) {
        request.getHeaders().put(X_CLIENT_ID, getClientId());
        request.getHeaders().put(X_ACCESS_TOKEN, accessToken);
    }
    
    private String generateAccessToken() throws JoseException {
        JwtClaims claims = new JwtClaims();
        claims.setIssuer(clientId);
        claims.setExpirationTimeMinutesInTheFuture(60*24); // 24hs
        claims.setGeneratedJwtId();
        claims.setIssuedAtToNow();
        String email = String.format("root+%s@api.smartcanvas.com", clientId);
        claims.setSubject(email);
        claims.setClaim("email", email);
        claims.setClaim("tokenType", "ACCESS");
        claims.setClaim("root", Boolean.TRUE);
        JsonWebSignature jws = new JsonWebSignature();
        jws.setPayload(claims.toJson());
        jws.setKey(getKey());
        jws.setHeader("typ", "JWT");
        jws.setAlgorithmHeaderValue(AlgorithmIdentifiers.HMAC_SHA256);
        String jwt = jws.getCompactSerialization();
        return jwt;
    }

    public String getClientId() {
        return clientId;
    }
    
    public String getClientSecret() {
        return clientSecret;
    }
    
    Key getKey() {
        return new HmacKey(clientSecret.getBytes());
    }

}
