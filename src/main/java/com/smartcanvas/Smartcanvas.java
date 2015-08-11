package com.smartcanvas;

import java.io.IOException;
import java.util.logging.Logger;

import org.jose4j.lang.JoseException;

import com.google.api.client.http.HttpBackOffUnsuccessfulResponseHandler;
import com.google.api.client.http.HttpExecuteInterceptor;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestFactory;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.json.JsonHttpContent;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.JsonObjectParser;
import com.google.api.client.util.ExponentialBackOff;
import com.google.api.client.util.Preconditions;
import com.smartcanvas.SmartcanvasUrls.CardApiUrl;
import com.smartcanvas.model.Card;
import com.smartcanvas.model.CardId;
import com.smartcanvas.model.CardSearchResult;
import com.smartcanvas.model.ModerationRequest;


public class Smartcanvas {

    private static final Logger LOGGER = Logger.getLogger(Smartcanvas.class.getSimpleName());
    
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
     * An accessor for creating requests for the Cards resource.
     *
     * <p>
     * The typical use is:
     * </p>
     * 
     * <pre>
     *   {@code SmartCanvas smartCanvas = new SmartCanvas(...);}
     *   {@code CardSearchResult result = smartCanvas.cards().search(parameters ...)}
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

        public CardSearchResult search(CardSearchRequest searchRequest) throws IOException {
            return getHttpRequest(searchRequest).execute().parseAs(CardSearchResult.class);
        }

        public CardId insert(Card card) throws IOException {
            return httpPostRequest(card).execute().parseAs(CardId.class);
        }

        public CardId update(Card card, String id) throws IOException {
            return httpPutRequest(card, id).execute().parseAs(CardId.class);
        }

        public CardId update(Long id, Card card) throws IOException {
            return update(card, String.valueOf(id));
        }
        
        public void delete(Long id) throws IOException {
            delete(String.valueOf(id));
        }
        
        public void delete(String id) throws IOException {
            httpDeleteRequest(id).execute();
        }
        
        public Card get(Long id) throws IOException {
            return get(String.valueOf(id));
        }
        
        public Card get(String id) throws IOException {
            CardApiUrl url = new CardApiUrl(useSandbox, id);
            HttpRequest request = requestFactory().buildGetRequest(url);
            return request.execute().parseAs(Card.class);
        }

        private HttpRequest getHttpRequest(CardSearchRequest searchRequest) throws IOException {
            HttpRequest request = requestFactory().buildGetRequest(searchRequest);
            return request;
        }

        private HttpRequest httpPostRequest(final Card card) throws IOException {
            CardApiUrl url = new CardApiUrl(useSandbox);
            JsonHttpContent content = new JsonHttpContent(jsonFactory, card);
            HttpRequest request = requestFactory().buildPostRequest(url, content);
            return request;
        }

        private HttpRequest httpPutRequest(final Card card, String id) throws IOException {
            CardApiUrl url = new CardApiUrl(useSandbox, id);
            JsonHttpContent content = new JsonHttpContent(jsonFactory, card);
            HttpRequest request = requestFactory().buildPutRequest(url, content);
            return request;
        }

        private HttpRequest httpDeleteRequest(String id) throws IOException {
            CardApiUrl url = new CardApiUrl(useSandbox, id);
            HttpRequest request = requestFactory().buildDeleteRequest(url);
            return request;
        }

    }
    
    public Moderations moderations() {
        return new Moderations();
    }

    public class Moderations {
        
        public void approve(Long cardId) throws IOException {
            moderate(ModerationRequest.approvalOf(cardId));
        }
        
        public void approve(CardId id) throws IOException {
            moderate(ModerationRequest.approvalOf(id.id()));
        }
        
        public void unapprove(Long cardId) throws IOException {
            moderate(ModerationRequest.pendingOf(cardId));
        }
        
        public void reject(Long cardId) throws IOException {
            moderate(ModerationRequest.rejectionOf(cardId));
        }
        
        private void moderate(ModerationRequest moderation) throws IOException {
            JsonHttpContent payload = new JsonHttpContent(jsonFactory, moderation);
            HttpRequest request = requestFactory().buildPostRequest(moderation.url(useSandbox), payload);
            request.execute();
        }

        
    }
    
    private HttpRequestFactory requestFactory() {
        return transport.createRequestFactory(new HttpRequestInitializer() {
            public void initialize(HttpRequest request) throws IOException {
                request.setParser(new JsonObjectParser(jsonFactory));
                request.setNumberOfRetries(NUMBER_OF_RETRIES_DEFAULT);
                request.setCurlLoggingEnabled(true);
                request.setUnsuccessfulResponseHandler(new HttpBackOffUnsuccessfulResponseHandler(new ExponentialBackOff()));
                authInitializer.initialize(request);
                if (executeInterceptor != null) {
                    request.setInterceptor(executeInterceptor);
                }
            }
        });
    }
}