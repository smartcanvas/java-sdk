package com.smartcanvas;

import com.google.api.client.util.DateTime;
import com.smartcanvas.model.Card.CardStatus;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class CardSearchRequestBuilderTest {
	
	@Test
	public void searchWithQuery() throws IOException {
		String queryTerm = "queryTerm";
		CardSearchRequest searchRequest = CardSearchRequest.builder().query(queryTerm).build();
		assertEquals(queryTerm, searchRequest.get("q"));
	}

	@Test
	public void searchByAuthor() throws IOException {
		CardSearchRequest searchRequest = CardSearchRequest.builder()
				.authorIds("fuechi@ciandt.com")
				.build();
		assertEquals("fuechi@ciandt.com", searchRequest.get("authorIds"));
	}

	@Test
	public void searchByMultipleAuthors() throws IOException {
		CardSearchRequest searchRequest = CardSearchRequest.builder()
				.authorIds("fuechi@ciandt.com", "gmoneda@ciandt.com")
				.build();
		
		assertTrue(((String)searchRequest.get("authorIds")).contains("fuechi@ciandt.com"));
		assertTrue(((String)searchRequest.get("authorIds")).contains("gmoneda@ciandt.com"));
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
	}
	
	@Test
	public void searchByMetaTags() throws IOException {
		CardSearchRequest searchRequest = CardSearchRequest.builder()
				.metaTags("CIT", "Collabore")
				.build();
		
		assertTrue(((String)searchRequest.get("metaTags")).contains("CIT"));
		assertTrue(((String)searchRequest.get("metaTags")).contains("Collabore"));
		System.out.println(searchRequest);
	}
	
	@Test
	public void searchByCommunityIds() throws IOException {
		CardSearchRequest searchRequest = CardSearchRequest.builder()
				.communityIds("Developers", "Intranet")
				.build();
		
		assertTrue(((String)searchRequest.get("communityIds")).contains("Developers"));
		assertTrue(((String)searchRequest.get("communityIds")).contains("Intranet"));
	}	
	
	@Test
	public void searchByproviderIds() throws IOException {
		CardSearchRequest searchRequest = CardSearchRequest.builder()
				.providerIds("ID")
				.build();
		
		assertTrue(((String)searchRequest.get("providerIds")).contains("ID"));
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
	}	
	
	@Test
	public void searchByEmbedString() throws IOException {
		CardSearchRequest searchRequest = CardSearchRequest.builder()
				.embed("authorIds", "contentProvider")
				.build();
		assertTrue(((String)searchRequest.get("embed")).contains("authorIds"));
		assertTrue(((String)searchRequest.get("embed")).contains("contentProvider"));
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
				.initDate(new DateTime("2015-05-21"))
				.endDate(new DateTime("2015-05-21"))
				.build();
		System.out.println(searchRequest);
		assertTrue(((String)searchRequest.get("initDate")).contains("2015-05-21"));
		assertTrue(((String)searchRequest.get("q")).contains("CI&T"));
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
		System.out.println(searchRequest);
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
		System.out.println(searchRequest);
	}
	
}
