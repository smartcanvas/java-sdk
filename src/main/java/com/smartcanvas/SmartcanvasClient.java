package com.smartcanvas;

import java.io.IOException;

import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpBackOffUnsuccessfulResponseHandler;
import com.google.api.client.http.HttpExecuteInterceptor;
import com.google.api.client.http.HttpHeaders;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestFactory;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.json.JsonHttpContent;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.repackaged.com.google.common.base.Preconditions;
import com.google.api.client.util.ExponentialBackOff;
import com.smartcanvas.model.Card;

public class SmartcanvasClient {

	private static final int NUMBER_OF_RETRIES_DEFAULT = 3;
    private static final String X_CLIENT_ID = "x-client-id";
	private static final String X_ACCESS_TOKEN = "x-access-token";
    private static final String API_DEFAULT_VERSION = "v1";
	private static final String API_DEFAULT_ENDPOINT = "http://api.smartcanvas.com";
	private String basePath;
	private HttpTransport transport;
	private JsonFactory jsonFactory;
	private String clientId;
	private HttpExecuteInterceptor executeInterceptor;

    public SmartcanvasClient(HttpTransport httpTransport, JsonFactory jsonFactory, String clientId) {
        this(httpTransport, jsonFactory, API_DEFAULT_ENDPOINT, clientId);
    }
	
	public SmartcanvasClient(HttpTransport httpTransport, JsonFactory jsonFactory, String basePath, String clientId) {
		super();
		this.transport = httpTransport;
		this.jsonFactory = jsonFactory;
		this.basePath = basePath;
		this.clientId = clientId;
	}

	/**
	 * For testing purposes
	 * 
	 * @param executeInterceptor
	 */
	public void setHttpExecuteInterceptor(
			HttpExecuteInterceptor executeInterceptor) {
		this.executeInterceptor = executeInterceptor;
	}


	/**
	 * @param card
	 * @throws IOException
	 */
	public void addCard(Card card)
			throws IOException {
// 		TODO fazer validacoes dos campos obrigatorios		
//		Preconditions.checkNotNull(card.containsKey("x-client-id"), "Access token required to make this API call");
//		Preconditions.checkNotNull(card, "event is mandatory");
//		Preconditions.checkState(card.containsKey("data"),
//				"data MUST contains a data property");
//		Preconditions.checkState(card.containsKey("action"),
//				"data MUST contains an action property");
		httpRequest(card).execute();
	}

	private HttpRequest httpRequest(final Card card)
			throws IOException {
		SmartcanvasApiUrl url = new SmartcanvasApiUrl();
		JsonHttpContent content = new JsonHttpContent(jsonFactory, card);
		HttpRequest request = requestFactory().buildPostRequest(url, content);
		request.setUnsuccessfulResponseHandler(new HttpBackOffUnsuccessfulResponseHandler(
				new ExponentialBackOff()));

		return request;
	}

	public class SmartcanvasApiUrl extends GenericUrl {
		final static String url = "%s/card/%s/cards";

		public SmartcanvasApiUrl() {
			super(String.format(url, basePath, API_DEFAULT_VERSION));
		}
	}

	private HttpRequestFactory requestFactory() {
		return transport.createRequestFactory(new HttpRequestInitializer() {
			public void initialize(HttpRequest request) {
				request.setNumberOfRetries(NUMBER_OF_RETRIES_DEFAULT);
				request.getHeaders().put(X_CLIENT_ID, clientId);
				//request.getHeaders().setAccept("ap");
				request.setCurlLoggingEnabled(true);
				if (executeInterceptor != null) {
					request.setInterceptor(executeInterceptor);
				}
			}
		});
	}

}