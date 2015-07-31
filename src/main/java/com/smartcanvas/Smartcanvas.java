package com.smartcanvas;

import com.google.api.client.http.*;
import com.google.api.client.http.json.JsonHttpContent;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.JsonObjectParser;
import com.google.api.client.util.ExponentialBackOff;
import com.google.api.client.util.Preconditions;
import com.smartcanvas.SmartcanvasUrls.CardApiUrl;
import com.smartcanvas.model.Card;
import com.smartcanvas.model.GetResponse;
import com.smartcanvas.model.PostResponse;
import org.jose4j.lang.JoseException;

import java.io.IOException;

public class Smartcanvas {

    private static final int NUMBER_OF_RETRIES_DEFAULT = 3;
    private HttpTransport transport;
    private JsonFactory jsonFactory;
    private HttpExecuteInterceptor executeInterceptor;
    private HttpRequestInitializer authInitializer;
    private boolean useSandbox = false;

    public Smartcanvas(HttpTransport httpTransport, JsonFactory jsonFactory, String clientId, String clientSecret)
            throws JoseException {
        this(httpTransport, jsonFactory, clientId, clientSecret, false);
    }

    public Smartcanvas(HttpTransport httpTransport, JsonFactory jsonFactory, String clientId, String clientSecret,
            boolean useSandbox) throws JoseException {
        super();
        this.transport = Preconditions.checkNotNull(httpTransport,
                "Required parameter httpTransport must be specified.");
        this.jsonFactory = Preconditions.checkNotNull(jsonFactory, "Required parameter jsonFactory must be specified.");
        this.authInitializer = new SmartcanvasAuthentication(clientId, clientSecret);
        this.useSandbox = useSandbox;
    }

    /**
     * An accessor for creating requests from the Cards resource.
     *
     * <p>
     * The typical use is:
     * </p>
     * 
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

        public GetResponse search(CardSearchRequest teste) throws IOException {
            return getHttpRequest(teste).execute().parseAs(GetResponse.class);
        }


        public PostResponse insert(Card card) throws IOException {
            return httpPostRequest(card).execute().parseAs(PostResponse.class);
        }

        public PostResponse update(Card card, String id) throws IOException {
            return httpPutRequest(card, id).execute().parseAs(PostResponse.class);
        }

        public void delete(String id) throws IOException {
            httpDeleteRequest(id).execute();
        }

        private HttpRequest getHttpRequest(CardSearchRequest teste) throws IOException {
            HttpRequest request = requestFactory().buildGetRequest(teste);
            return request;
        }

        private HttpRequest httpPostRequest(final Card card) throws IOException {
            CardApiUrl url = new CardApiUrl(useSandbox);
            JsonHttpContent content = new JsonHttpContent(jsonFactory, card);
            HttpRequest request = requestFactory().buildPostRequest(url, content);
            request.setUnsuccessfulResponseHandler(new HttpBackOffUnsuccessfulResponseHandler(new ExponentialBackOff()));
            return request;
        }

        private HttpRequest httpPutRequest(final Card card, String id) throws IOException {
            CardApiUrl url = new CardApiUrl(useSandbox, id);
            JsonHttpContent content = new JsonHttpContent(jsonFactory, card);
            HttpRequest request = requestFactory().buildPutRequest(url, content);
            request.setUnsuccessfulResponseHandler(new HttpBackOffUnsuccessfulResponseHandler(new ExponentialBackOff()));
            return request;
        }

        private HttpRequest httpDeleteRequest(String id) throws IOException {
            CardApiUrl url = new CardApiUrl(useSandbox, id);
            HttpRequest request = requestFactory().buildDeleteRequest(url);
            request.setUnsuccessfulResponseHandler(new HttpBackOffUnsuccessfulResponseHandler(new ExponentialBackOff()));
            return request;
        }

    }

    private HttpRequestFactory requestFactory() {
        return transport.createRequestFactory(new HttpRequestInitializer() {
            public void initialize(HttpRequest request) throws IOException {
                request.setParser(new JsonObjectParser(jsonFactory));
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
            // if (response.getStatusCode() ==
            // HttpStatusCodes.STATUS_CODE_UNAUTHORIZED) {
            // refreshToken();
            // }
            return false;
        }
    }

}