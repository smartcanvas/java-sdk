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
import com.google.api.client.json.JsonObjectParser;
import com.google.api.client.util.ExponentialBackOff;
import com.google.api.client.util.Key;
import com.google.api.client.util.Preconditions;
import com.smartcanvas.model.Card;
import com.smartcanvas.model.GetResponse;
import com.smartcanvas.model.PostResponse;


public class Smartcanvas {

    private static final int NUMBER_OF_RETRIES_DEFAULT = 3;
    private static final String DEFAULT_ROOT_URL = "https://api.smartcanvas.com/";
    
    private String rootUrl;
    private HttpTransport transport;
    private JsonFactory jsonFactory;
    private HttpExecuteInterceptor executeInterceptor;
    private HttpRequestInitializer authInitializer;

    public Smartcanvas(HttpTransport httpTransport, JsonFactory jsonFactory, String clientId, String clientSecret) throws JoseException {
        this(httpTransport, jsonFactory, DEFAULT_ROOT_URL, clientId, clientSecret);
    }

    public Smartcanvas(HttpTransport httpTransport, JsonFactory jsonFactory, String rootUrl, String clientId,
            String clientSecret) throws JoseException {
        super();
        this.transport = Preconditions.checkNotNull(httpTransport, "Required parameter httpTransport must be specified.");
        this.jsonFactory = Preconditions.checkNotNull(jsonFactory, "Required parameter jsonFactory must be specified.");
        this.rootUrl = Preconditions.checkNotNull(rootUrl, "Required parameter rootUrl must be specified.");
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
        
        public static final String DEFAULT_SERVICE_PATH = "card/v1";
        
        	
        		
        public class CardApiUrl extends GenericUrl {
            
            final static String url = "%s%s/cards";
            final static String urlUpdate = "%s%s/cards/%s";
            @Key("q")
            String query;
            
            @Key
            String status;
            
            public CardApiUrl() {
                super(String.format(url, rootUrl, DEFAULT_SERVICE_PATH));
            }    
            
            public CardApiUrl(String id) {
            	super(String.format(urlUpdate, rootUrl, DEFAULT_SERVICE_PATH,id));
            }
            
        }
        
        
        
        public GetResponse search(String query) throws IOException {
            return getHttpRequest(query).execute().parseAs(GetResponse.class);
        }
      
        public PostResponse insert(Card card) throws IOException {
            return httpPostRequest(card).execute().parseAs(PostResponse.class);
        }
        
	    public PostResponse update(Card card, String id) throws IOException {
	      	return httpPutRequest(card, id).execute().parseAs(PostResponse.class);
	        }
        
        private HttpRequest getHttpRequest(String query) throws IOException {
            CardApiUrl url = new CardApiUrl();
            url.query = query;
            url.status = "approved";
            HttpRequest request = requestFactory().buildGetRequest(url);
            return request;
        }
        
        private HttpRequest httpPostRequest(final Card card) throws IOException {
            CardApiUrl url = new CardApiUrl();
            JsonHttpContent content = new JsonHttpContent(jsonFactory, card);
            HttpRequest request = requestFactory().buildPostRequest(url, content);
            request.setUnsuccessfulResponseHandler(new HttpBackOffUnsuccessfulResponseHandler(new ExponentialBackOff()));
            return request;
        }
        
        
        private HttpRequest httpPutRequest(final Card card, String id) throws IOException {
        	
            CardApiUrl url = new CardApiUrl(id);
            JsonHttpContent content = new JsonHttpContent(jsonFactory, card);
            HttpRequest request = requestFactory().buildPutRequest(url, content);
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
//            if (response.getStatusCode() == HttpStatusCodes.STATUS_CODE_UNAUTHORIZED) {
//                refreshToken();
//            }
            return false;
        }
    }

}