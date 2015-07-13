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
import com.smartcanvas.SmartcanvasClient;
import com.smartcanvas.model.Card;
import com.smartcanvas.Event;

public class SmartcanvasClientTests {

	static final HttpTransport HTTP_TRANSPORT = new NetHttpTransport();
	static final JsonFactory JSON_FACTORY = new JacksonFactory();
	private SmartcanvasClient smartcanvas;
	private static final String BASE_PATH = "http://api.smartcanvas.com";
	private static final String CLIENT_ID = "yYSr9igrmPkR";

	@Before
	public void setUp() {
		smartcanvas = new SmartcanvasClient(HTTP_TRANSPORT, JSON_FACTORY, BASE_PATH, CLIENT_ID);
	}

	@Test
	public void test() throws IOException {
		Card card = new Card();
		card.set("mnemonic", "Mumnemonic");
		smartcanvas.addCard(card);
	}

}
