package com.ciandt.brain.client;

import java.io.IOException;
import java.util.Map;

import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpBackOffUnsuccessfulResponseHandler;
import com.google.api.client.http.HttpExecuteInterceptor;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestFactory;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.json.JsonHttpContent;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.util.ExponentialBackOff;
import com.google.api.client.util.Preconditions;

/**
 * Brain REST API Java Client
 * Easiest way to send events to the Brain within a Java application
 * 
* <p/>
 * Example usage:
 * <p/>
 * <pre>
 *		BrainClient brain = new BrainClient(HTTP_TRANSPORT, JSON_FACTORY, projectId);
 *		Map eventData = new HashMap();
 *		eventData.put("p1", 1231232343);
 *		eventData.put("userId", "anonymous");
 *		Event event = new Event("pin_sent", eventData);
 *		brain.addEvent(eventCollection, event);
 * </pre> 
 * 
 * @author fabio
 * @version 1.0
 */
public class BrainClient {

	private final String apiVersion = "1.0";
	private String basePath = "https://digitalmarketingnextdev.appspot.com/";
	private HttpTransport transport;
	private JsonFactory jsonFactory;
	private String projectId;
	private HttpExecuteInterceptor executeInterceptor;


	public BrainClient(HttpTransport httpTransport, JsonFactory jsonFactory,
			String projectId, String basePath) {
		super();
		this.transport = httpTransport;
		this.jsonFactory = jsonFactory;
		this.projectId = projectId;
		this.basePath = basePath;
	}
	
	public BrainClient(HttpTransport httpTransport, JsonFactory jsonFactory,
			String projectId) {
		super();
		this.transport = httpTransport;
		this.jsonFactory = jsonFactory;
		this.projectId = projectId;
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
	 * @param eventCollection
	 * @param events
	 * @throws IOException
	 */
	public void addEvents(String eventCollection, Event[] events)
			throws IOException {
		for (Event evt : events) {
			addEvent(eventCollection, evt);
		}
	}

	public void addEvent(String eventCollection, Map<String, Object> event) throws IOException {
		addEvent(eventCollection, new Event(eventCollection, event));
	}
	
	/**
	 * @param eventCollection
	 * @param event
	 * @throws IOException
	 */
	public void addEvent(String eventCollection, Event event)
			throws IOException {
		Preconditions.checkNotNull(eventCollection, "");
		Preconditions.checkNotNull(event, "");
		Preconditions.checkState(event.containsKey("data"),
				"data MUST contains a data property");
		Preconditions.checkState(event.containsKey("action"),
				"data MUST contains an action property");
		httpRequest(eventCollection, event).execute();
	}

	private HttpRequest httpRequest(String eventCollection, Event event)
			throws IOException {

		BrainApiUrl url = new BrainApiUrl(projectId, eventCollection);
		JsonHttpContent content = new JsonHttpContent(jsonFactory, event);

		HttpRequest request = requestFactory().buildPostRequest(url, content);

		request.setUnsuccessfulResponseHandler(new HttpBackOffUnsuccessfulResponseHandler(
				new ExponentialBackOff()));

		return request;
	}

	public class BrainApiUrl extends GenericUrl {
		
		final static String url = "%s/%s/projects/%s/events/%s";

		public BrainApiUrl(String projectId, String eventCollection) {
			super(String.format(url, basePath, apiVersion, projectId,
					eventCollection));
		}
	}

	private HttpRequestFactory requestFactory() {
		return transport.createRequestFactory(new HttpRequestInitializer() {
			public void initialize(HttpRequest request) {
				request.setNumberOfRetries(5);
				if (executeInterceptor != null) {
					request.setInterceptor(executeInterceptor);
				}
			}
		});
	}

}