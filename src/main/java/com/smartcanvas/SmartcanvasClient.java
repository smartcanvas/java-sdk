package com.smartcanvas;

import java.io.IOException;
import java.util.Map;

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
import com.google.api.client.util.ExponentialBackOff;
import com.google.api.client.util.Preconditions;
import com.smartcanvas.model.Card;

public class SmartcanvasClient {

	private static final String apiVersion = "v1";
	private String basePath;
	private HttpTransport transport;
	private JsonFactory jsonFactory;
	private String apiKey;
	private HttpExecuteInterceptor executeInterceptor;

	public SmartcanvasClient(HttpTransport httpTransport, JsonFactory jsonFactory, String basePath, String apiKey) {
		super();
		this.transport = httpTransport;
		this.jsonFactory = jsonFactory;
		this.basePath = basePath;
		this.apiKey = apiKey;
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
//		Preconditions.checkNotNull(eventCollection, "eventCollection will be used as action and therefore is mandatory");
//		Preconditions.checkNotNull(card, "event is mandatory");
//		Preconditions.checkState(card.containsKey("data"),
//				"data MUST contains a data property");
//		Preconditions.checkState(card.containsKey("action"),
//				"data MUST contains an action property");
		httpRequest( card).execute();
	}

	private HttpRequest httpRequest(final Card card)
			throws IOException {
		SmartcanvasApiUrl url = new SmartcanvasApiUrl();
		JsonHttpContent content = new JsonHttpContent(jsonFactory, card);
		HttpRequest request = requestFactory().buildPostRequest(url, content);
		
		HttpHeaders headers = new HttpHeaders();
		headers.put("x-client-id", apiKey);
		headers.put("x-d1-tenant", "labs");
		request.setHeaders(headers);
		request.setCurlLoggingEnabled(true);
		request.setUnsuccessfulResponseHandler(new HttpBackOffUnsuccessfulResponseHandler(
				new ExponentialBackOff()));

		return request;
	}

	public class SmartcanvasApiUrl extends GenericUrl {

		final static String url = "%s/card/%s/cards";

		public SmartcanvasApiUrl() {
			super(String.format(url, basePath, apiVersion));
		}
	}

	private HttpRequestFactory requestFactory() {
		return transport.createRequestFactory(new HttpRequestInitializer() {
			public void initialize(HttpRequest request) {
				request.setNumberOfRetries(5);
				request.getHeaders().setAuthorization(apiKey);
				if (executeInterceptor != null) {
					request.setInterceptor(executeInterceptor);
				}
			}
		});
	}

}