package com.smartcanvas;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.Key;
import com.smartcanvas.BrainClient;
import com.smartcanvas.Event;

public class BrainClientTests {

	static final HttpTransport HTTP_TRANSPORT = new NetHttpTransport();
	static final JsonFactory JSON_FACTORY = new JacksonFactory();
	private BrainClient brain;
	private static final String PROJECT_ID = "brain_client_java_api_integration_tests";
	private static final String BASE_PATH = "https://digitalmarketingnextdev.appspot.com/";
	private static final String API_KEY = "b6c721372aee4fd485127e5e0075d63f";

	@Before
	public void setUp() {
		brain = new BrainClient(HTTP_TRANSPORT, JSON_FACTORY, PROJECT_ID, BASE_PATH, API_KEY);
	}
	
	class PinSubmission {
		@Key
		String pinNumber = "1234";
		@Key
		String pinDescription = "sdafasf";
		@Key
		String userId = "fuedni@ciand.com";
	}

	@Test
	public void test() throws IOException {
		String eventCollection = "pin_submited";
		Event event = new Event(eventCollection, new PinSubmission());
		brain.addEvent(eventCollection, event);
	}

	@Test
	public void testEventAsMap() throws IOException {
		String eventCollection = "pin_submited";
		Map<String, Object> eventData = new HashMap<String, Object>();
		eventData.put("pinNumber", 1231232343);
		eventData.put("userId", "anonymous");
		Event event = new Event(eventCollection, eventData);
		brain.addEvent(eventCollection, event);
	}

}
