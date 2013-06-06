package com.ciandt.brain.client;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.testing.http.MockHttpTransport;
import com.google.api.client.util.Key;

public class BrainClientTests {

	static final HttpTransport HTTP_TRANSPORT = MockHttpTransport.builder()
			.build();
	static final JsonFactory JSON_FACTORY = new JacksonFactory();
	private BrainClient brain;

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
		String projectId = "cocacola";
		String eventCollection = "pin_submited";
		brain = new BrainClient(HTTP_TRANSPORT, JSON_FACTORY, projectId);
//		brain.setHttpExecuteInterceptor(new HttpExecuteInterceptor() {
//			public void intercept(HttpRequest request) throws IOException {
//				JsonHttpContent content = (JsonHttpContent) request.getContent();
//				System.out.println(content.getData());
//			}
//		});

		Event event = new Event(eventCollection, new PinSubmission());
		brain.addEvent(eventCollection, event);
	}

	@Test
	public void testEventAsMap() throws IOException {
		String projectId = "cocacola";
		String eventCollection = "pin_submited";
		BrainClient brain = new BrainClient(HTTP_TRANSPORT, JSON_FACTORY, projectId);
		Map<String, Object> eventData = new HashMap<String, Object>();
		eventData.put("pinNumber", 1231232343);
		eventData.put("userId", "anonymous");
		Event event = new Event(eventCollection, eventData);
		brain.addEvent(eventCollection, event);
	}

}
