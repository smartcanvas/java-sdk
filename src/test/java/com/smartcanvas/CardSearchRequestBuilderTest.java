package com.smartcanvas;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.IOException;

import org.jose4j.lang.JoseException;
import org.junit.Test;

import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.DateTime;
import com.smartcanvas.model.Card.CardStatus;

public class CardSearchRequestBuilderTest {
	


		private Smartcanvas smartcanvas;
		static final HttpTransport HTTP_TRANSPORT = new NetHttpTransport();
		static final JsonFactory JSON_FACTORY = new JacksonFactory();
		private static final String CLIENT_ID = "yYSr9igrmPkR";
		private static final String CLIENT_SECRET = "ce4a3f668a3d9ca30a6653a005f86b063906769bad7f27f1a83241c267028e89";

		public CardSearchRequestBuilderTest() throws JoseException {
			smartcanvas = new Smartcanvas(HTTP_TRANSPORT, JSON_FACTORY, CLIENT_ID, CLIENT_SECRET);
		}

	@Test
	public void searchWithQuery() throws IOException {
		String queryTerm = "queryTerm";
		CardSearchRequest searchRequest = CardSearchRequest.builder().query(queryTerm).build();
		assertEquals(queryTerm, searchRequest.get("query"));
		smartcanvas.cards().search(searchRequest);
	}

	@Test
	public void searchByAuthor() throws IOException {
		CardSearchRequest searchRequest = CardSearchRequest.builder()
				//authorIds must have a email
				.authorIds("gmoneda@ciandt.com")
				.build();
		assertEquals("gmoneda@ciandt.com", searchRequest.get("authorIds"));
		smartcanvas.cards().search(searchRequest);
	}

	@Test
	public void searchByMultipleAuthors() throws IOException {
		CardSearchRequest searchRequest = CardSearchRequest.builder()
				.authorIds("fuechi@ciandt.com", "gmoneda@ciandt.com")
				.build();
		
		assertTrue(((String)searchRequest.get("authorIds")).contains("fuechi@ciandt.com"));
		assertTrue(((String)searchRequest.get("authorIds")).contains("gmoneda@ciandt.com"));
		smartcanvas.cards().search(searchRequest);
	}
	
	@Test
	public void searchByCategories() throws IOException {
		CardSearchRequest searchRequest = CardSearchRequest.builder()
				.categories("shopping", "cards", "people", "ciandt")
				.build();
		
		assertTrue(((String)searchRequest.get("categories")).contains("shopping"));
		assertTrue(((String)searchRequest.get("categories")).contains("cards"));
		assertTrue(((String)searchRequest.get("categories")).contains("people"));
		assertTrue(((String)searchRequest.get("categories")).contains("ciandt"));
		smartcanvas.cards().search(searchRequest);
	}
	
	@Test
	public void searchByMetaTags() throws IOException {
		CardSearchRequest searchRequest = CardSearchRequest.builder()
				.query("title")
				.metaTags("ciandt", "Collabore")
				.build();
		
		assertTrue(((String)searchRequest.get("metaTags")).contains("ciandt"));
		assertTrue(((String)searchRequest.get("metaTags")).contains("Collabore"));
		smartcanvas.cards().search(searchRequest);

	}
	
	@Test
	public void searchByCommunityIds() throws IOException {
		CardSearchRequest searchRequest = CardSearchRequest.builder()
				.communityIds("Developers", "Intranet")
				.build();
		
		assertTrue(((String)searchRequest.get("communityIds")).contains("Developers"));
		assertTrue(((String)searchRequest.get("communityIds")).contains("Intranet"));
		smartcanvas.cards().search(searchRequest);
		
	}	
	
	@Test
	public void searchByproviderIds() throws IOException {
		CardSearchRequest searchRequest = CardSearchRequest.builder()
				.providerIds("ID")
				.build();
		
		assertTrue(((String)searchRequest.get("providerIds")).contains("ID"));
		smartcanvas.cards().search(searchRequest);
	}	
	
	@Test
	public void searchByFields() throws IOException {
		CardSearchRequest searchRequest = CardSearchRequest.builder()
				//Fields to include in the response. If omitted, the response will contain all fields
				.query("ciandt")
				.fields("title", "summary", "content")
				.build();
		
		assertTrue(((String)searchRequest.get("fields")).contains("title"));
		assertTrue(((String)searchRequest.get("fields")).contains("summary"));
		assertTrue(((String)searchRequest.get("fields")).contains("content"));
		smartcanvas.cards().search(searchRequest);
	}	
	
	@Test
	public void searchByEmbedString() throws IOException {
		CardSearchRequest searchRequest = CardSearchRequest.builder()
				.embed("authorIds", "contentProvider")
				.build();
		
		assertTrue(((String)searchRequest.get("embed")).contains("authorIds"));
		assertTrue(((String)searchRequest.get("embed")).contains("contentProvider"));
		smartcanvas.cards().search(searchRequest);
	}
	
	@Test
	public void searchByDate() throws IOException {
		CardSearchRequest searchRequest = CardSearchRequest.builder()
				.query("CI&T")
				.status(CardStatus.APPROVED)
				.locale("us-en")
				.limit(10101)
				.offset(201102)				
				//initDate accept (YYYY-mm-dd)  * Look for Datetime.class to more info
				.initDate(new DateTime("2015-03-21"))
				.endDate(new DateTime("2015-05-01"))
				.build();
		
		assertTrue(((String)searchRequest.get("initDate")).contains("2015-03-21"));
		assertTrue(((String)searchRequest.get("query")).contains("CI&T"));
		smartcanvas.cards().search(searchRequest);
	
	}
	
	@Test
	public void searchByMaxDate() throws IOException {
		CardSearchRequest searchRequest = CardSearchRequest.builder()
				.query("CI&T")
				.status(CardStatus.APPROVED)
				//pt-br or us-en
				.locale("us-en")
				.maxAge(2)
				.build();
		smartcanvas.cards().search(searchRequest);
	}
	
	@Test
	public void searchByAllFields() throws IOException {
		CardSearchRequest searchRequest = CardSearchRequest.builder()
				.query("CI&T")
				.status(CardStatus.APPROVED)
				.authorIds("fuechi@ciandt.com", "gmoneda@ciandt.com")
				.communityIds("play", "store")
				.providerIds("providerID1", "providerID2")
				.categories("shopping", "cards", "people", "ciandt")
				.fields("title", "community", "content")
				//pt-br or us-en
				.locale("us-en")
				.metaTags("metatags", "Tag")
				.embed("authorIds", "id")
				//.maxAge(2)
				.limit(9)
				.offset(2)	
				.initDate(new DateTime("2015-15-01"))
				.endDate(new DateTime("2015-15-03"))
				//.decayment(0.5D)
				//.jsonExtendedData("Not Implemmented yet")
				.build();
		smartcanvas.cards().search(searchRequest);
	}
	
}
