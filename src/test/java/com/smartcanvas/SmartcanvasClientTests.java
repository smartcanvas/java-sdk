package com.smartcanvas;

import java.io.IOException;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;

import com.smartcanvas.SmartcanvasClient;
import com.smartcanvas.model.Card;
import com.smartcanvas.model.ContentProvider;

import java.util.ArrayList;

public class SmartcanvasClientTests {

	static final HttpTransport HTTP_TRANSPORT = new NetHttpTransport();
	static final JsonFactory JSON_FACTORY = new JacksonFactory();
	private SmartcanvasClient smartcanvas;
	private static final String CLIENT_ID = "yYSr9igrmPkR";
	private List<String> lista;

	@Before
	public void setUp() {
		smartcanvas = new SmartcanvasClient(HTTP_TRANSPORT, JSON_FACTORY, CLIENT_ID);
	}

	@Test
	public void addSimpleCard() throws IOException {
		Card card = new Card(givenProvider());
		card.setTitle("Titulo");
		smartcanvas.addCard(card);
	}

	@Test
	public void addCategories() throws IOException {

		lista = new ArrayList<String>();
		lista.add("categoria 1");
		lista.add("categoria 2");

		Card card = new Card(givenProvider());
		card.setAutoApprove(true);
		card.setCategories(lista);
		smartcanvas.addCard(card);

	}

	@Test
	public void addContentProvider() throws IOException {
		Card card = new Card(givenProvider());
		card.setTitle("Titulo");

		smartcanvas.addCard(card);
	}

	private ContentProvider givenProvider() {
		return new ContentProvider("ID provider", "GooglePlus", "gmoneda");
	}

}