package com.smartcanvas;

import com.google.api.client.http.HttpExecuteInterceptor;
import com.google.api.client.http.HttpRequest;
import com.smartcanvas.model.Card;
import org.jose4j.lang.JoseException;
import org.junit.Test;

import java.io.IOException;
import java.util.UUID;

/**
 * Created by gmoneda on 17/08/15.
 */
public class CardInteceptorTest extends AbstractSmartCanvasIntegrationTests {

    public CardInteceptorTest() throws JoseException {
        super();
    }


    @Test
    public void directCardPost() throws IOException {

        Card buildCard = Card.newBuilder()
                .withContentProvider(givenProvider())
                .withTitle("This is a Direct Card, created without Sensidia API")
                .withMnemonic("DirectCard")
                .withAuthor("authorID", "authorName")
                .withAutoApprove(true)
                .build();
        smartcanvas.cards().insert(buildCard);
    }

    private class InterceptorTenantId implements HttpExecuteInterceptor {
        public void intercept(HttpRequest request) throws IOException {
            request.getHeaders().put("x-tenant-id", "labsfw");
        }
    }

    private Card.ContentProvider givenProvider() {
        UUID uuid = UUID.randomUUID();
        return new Card.ContentProvider("java-sdk-unit-tests", uuid.toString(), "gmoneda");

    }
}

