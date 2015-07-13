package com.smartcanvas;

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
 * Brain REST API Java Client: the easiest way to send events to the Brain within a
 * Java application
 * 
 * <p/>
 * Example usage:
 * <p/>
 * 
 * <pre>
 * BrainClient brain = new BrainClient(HTTP_TRANSPORT, JSON_FACTORY, projectId, basePath, apiKey);
 * Map eventData = new HashMap();
 * eventData.put(&quot;p1&quot;, 1231232343);
 * eventData.put(&quot;userId&quot;, &quot;anonymous&quot;);
 * Event event = new Event(&quot;pin_sent&quot;, eventData);
 * brain.addEvent(eventCollection, event);
 * </pre>
 * 
 * @author D1
 * @version 1.0
 */
public class BrainClient {

	private static final String apiVersion = "1.0";
	private String basePath;
	private HttpTransport transport;
	private JsonFactory jsonFactory;
	private String projectId;
	private String apiKey;
	private HttpExecuteInterceptor executeInterceptor;

	public BrainClient(HttpTransport httpTransport, JsonFactory jsonFactory,
			String projectId, String basePath, String apiKey) {
		super();
		this.transport = httpTransport;
		this.jsonFactory = jsonFactory;
		this.projectId = projectId;
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
	 * @param eventCollection
	 * @param events
	 * @throws IOException
	 */
	public void addEvents(final String eventCollection, Event[] events)
			throws IOException {
		for (Event evt : events) {
			addEvent(eventCollection, evt);
		}
	}

	public void addEvent(final String eventCollection, Map<String, Object> event)
			throws IOException {
		addEvent(eventCollection, new Event(eventCollection, event));
	}

	public void addEvent(final String eventCollection, String jsonEvent)
			throws IOException {
		throw new RuntimeException("Not Implemented");
	}

	/**
	 * @param eventCollection
	 * @param event
	 * @throws IOException
	 */
	public void addEvent(final String eventCollection, Event event)
			throws IOException {
		Preconditions.checkNotNull(eventCollection, "eventCollection will be used as action and therefore is mandatory");
		Preconditions.checkNotNull(event, "event is mandatory");
		Preconditions.checkState(event.containsKey("data"),
				"data MUST contains a data property");
		Preconditions.checkState(event.containsKey("action"),
				"data MUST contains an action property");
		httpRequest(eventCollection, event).execute();
	}

	private HttpRequest httpRequest(final String eventCollection, Event event)
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
				request.getHeaders().setAuthorization(apiKey);
				if (executeInterceptor != null) {
					request.setInterceptor(executeInterceptor);
				}
			}
		});
	}

}