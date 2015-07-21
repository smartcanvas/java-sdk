package com.smartcanvas;

import java.io.IOException;

import org.jose4j.lang.JoseException;

import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpBackOffUnsuccessfulResponseHandler;
import com.google.api.client.http.HttpExecuteInterceptor;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestFactory;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.HttpResponse;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.HttpUnsuccessfulResponseHandler;
import com.google.api.client.http.json.JsonHttpContent;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.util.ExponentialBackOff;
import com.google.api.client.util.Preconditions;
import com.smartcanvas.model.Card;

public class Smartcanvas {

    private static final int NUMBER_OF_RETRIES_DEFAULT = 3;
    private static final String API_DEFAULT_VERSION = "v1";
    private static final String API_DEFAULT_ENDPOINT = "https://api.smartcanvas.com";
    private String basePath;
    private HttpTransport transport;
    private JsonFactory jsonFactory;
    private HttpExecuteInterceptor executeInterceptor;
    private HttpRequestInitializer authInitializer;

    public Smartcanvas(HttpTransport httpTransport, JsonFactory jsonFactory, String clientId, String clientSecret) throws JoseException {
        this(httpTransport, jsonFactory, API_DEFAULT_ENDPOINT, clientId, clientSecret);
    }

    public Smartcanvas(HttpTransport httpTransport, JsonFactory jsonFactory, String basePath, String clientId,
            String clientSecret) throws JoseException {
        super();
        this.transport = Preconditions.checkNotNull(httpTransport, "Required parameter httpTransport must be specified.");
        this.jsonFactory = Preconditions.checkNotNull(jsonFactory, "Required parameter jsonFactory must be specified.");
        this.basePath = Preconditions.checkNotNull(basePath);
        this.authInitializer = new SmartcanvasAuthentication(clientId, clientSecret);
    }

    /**
     * An accessor for creating requests from the Cards resource.
     *
     * <p>The typical use is:</p>
     * <pre>
     *   {@code SmartCanvas smartCanvas = new SmartCanvas(...);}
     *   {@code SmartCanvas.Cards.List request = smartCanvas.cards().list(parameters ...)}
     * </pre>
     *
     * @return the resource collection
     */
    public Cards cards() {
      return new Cards();
    }

    /**
     * The "cards" collection of methods.
     */
    public class Cards {

    }
    
    /**
     * @param card
     * @throws IOException
     */
    public void addCard(Card card) throws IOException {
        // TODO fazer validacoes dos campos obrigatorios
        // Preconditions.checkNotNull(card.containsKey("x-client-id"),
        // "Access token required to make this API call");
        // Preconditions.checkNotNull(card, "event is mandatory");
        // Preconditions.checkState(card.containsKey("data"),
        // "data MUST contains a data property");
        // Preconditions.checkState(card.containsKey("action"),
        // "data MUST contains an action property");
        httpRequest(card).execute();
    }

    private HttpRequest httpRequest(final Card card) throws IOException {
        CardApiUrl url = new CardApiUrl();
        JsonHttpContent content = new JsonHttpContent(jsonFactory, card);
        HttpRequest request = requestFactory().buildPostRequest(url, content);
        request.setUnsuccessfulResponseHandler(new HttpBackOffUnsuccessfulResponseHandler(new ExponentialBackOff()));
        return request;
    }

    public class CardApiUrl extends GenericUrl {
        final static String url = "%s/card/%s/cards";
        public CardApiUrl() {
            super(String.format(url, basePath, API_DEFAULT_VERSION));
        }
    }

    private HttpRequestFactory requestFactory() {
        return transport.createRequestFactory(new HttpRequestInitializer() {
            public void initialize(HttpRequest request) throws IOException {
                request.setNumberOfRetries(NUMBER_OF_RETRIES_DEFAULT);
                request.setCurlLoggingEnabled(true);
                authInitializer.initialize(request);
                if (executeInterceptor != null) {
                    request.setInterceptor(executeInterceptor);
                }
            }
        });
    }

    public static class RefreshTokenHandler implements HttpUnsuccessfulResponseHandler {
        @Override
        public boolean handleResponse(HttpRequest request, HttpResponse response, boolean retrySupported)
                throws IOException {
//            if (response.getStatusCode() == HttpStatusCodes.STATUS_CODE_UNAUTHORIZED) {
//                refreshToken();
//            }
            return false;
        }
    }

}