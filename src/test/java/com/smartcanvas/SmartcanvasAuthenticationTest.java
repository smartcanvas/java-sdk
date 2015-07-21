package com.smartcanvas;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.IOException;

import org.jose4j.jwt.JwtClaims;
import org.jose4j.jwt.MalformedClaimException;
import org.jose4j.jwt.consumer.InvalidJwtException;
import org.jose4j.jwt.consumer.JwtConsumer;
import org.jose4j.jwt.consumer.JwtConsumerBuilder;
import org.jose4j.lang.JoseException;
import org.junit.Test;

import com.google.api.client.http.HttpRequest;
import com.google.api.client.testing.http.HttpTesting;
import com.google.api.client.testing.http.MockHttpTransport;

public class SmartcanvasAuthenticationTest {

    static final String CLIENT_ID = "Aladdin";
    static final String CLIENT_SECRET = "9515e65f46cb737cd8c191db2fd80bbd05686e5992b241e8ad7727510b7142e6";

    @Test
    public void testConstructor() throws JoseException {
        SmartcanvasAuthentication auth = new SmartcanvasAuthentication(CLIENT_ID, CLIENT_SECRET);
        assertEquals(CLIENT_ID, auth.getClientId());
        assertEquals(CLIENT_SECRET, auth.getClientSecret());
    }

    @Test
    public void testInitialize() throws IOException, InvalidJwtException, MalformedClaimException, JoseException {
        SmartcanvasAuthentication auth = new SmartcanvasAuthentication(CLIENT_ID, CLIENT_SECRET);
        HttpRequest request = new MockHttpTransport().createRequestFactory().buildGetRequest(
                HttpTesting.SIMPLE_GENERIC_URL);
        auth.initialize(request);
        assertEquals(CLIENT_ID, request.getHeaders().get(SmartcanvasAuthentication.X_CLIENT_ID));
        String jwt = (String) request.getHeaders().get(SmartcanvasAuthentication.X_ACCESS_TOKEN);

        
        assertNotNull(jwt);
        JwtConsumer jwtConsumer = new JwtConsumerBuilder()
            .setRequireExpirationTime()
            .setRequireIssuedAt()
            .setAllowedClockSkewInSeconds(30)
            .setRequireSubject()
            .setVerificationKey(auth.getKey())
            .build();
        
        JwtClaims claims = jwtConsumer.processToClaims(jwt);
        assertEquals(CLIENT_ID, claims.getIssuer());
        assertTrue((Boolean)claims.getClaimValue("root"));
    }

}
