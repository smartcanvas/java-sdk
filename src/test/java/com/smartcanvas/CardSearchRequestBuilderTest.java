package com.smartcanvas;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.IOException;

import org.joda.time.LocalTime;
import org.junit.Test;

import com.google.api.client.util.DateTime;
import com.smartcanvas.model.Card.CardStatus;

public class CardSearchRequestBuilderTest {

	@Test
	public void searchWithQuery() throws IOException {
		String queryTerm = "queryTerm";
		CardSearchRequest searchRequest = CardSearchRequest.builder().query(queryTerm).build();
		assertEquals(queryTerm, searchRequest.get("query"));
	}

	@Test
	public void searchByAuthor() throws IOException {
		CardSearchRequest searchRequest = CardSearchRequest.builder()
				.authorIds("fuechi")
				.build();
		assertEquals("fuechi", searchRequest.get("authorIds"));
	}

	@Test
	public void searchByMultipleAuthors() throws IOException {
		CardSearchRequest searchRequest = CardSearchRequest.builder()
				.authorIds("fuechi", "gmoneda")
				.build();
		assertEquals("fuechi,gmoneda", searchRequest.get("authorIds"));
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
				.metaTags("metatags", "Tag", " ")
				.build();
		
		assertTrue(((String)searchRequest.get("metaTags")).contains("metatags"));
		assertTrue(((String)searchRequest.get("metaTags")).contains("Tag"));
		assertTrue(((String)searchRequest.get("metaTags")).contains(" "));
	}
	
	@Test
	public void searchByCommunityIds() throws IOException {
		CardSearchRequest searchRequest = CardSearchRequest.builder()
				.communityIds("play", "store")
				.build();
		
		assertTrue(((String)searchRequest.get("communityIds")).contains("play"));
		assertTrue(((String)searchRequest.get("communityIds")).contains("store"));
		
	}	
	
	@Test
	public void searchByproviderIds() throws IOException {
		CardSearchRequest searchRequest = CardSearchRequest.builder()
				.providerIds("play", "store")
				.build();
		
		assertTrue(((String)searchRequest.get("providerIds")).contains("play"));
		assertTrue(((String)searchRequest.get("providerIds")).contains("store"));
		
	}	
	
	@Test
	public void searchByFields() throws IOException {
		CardSearchRequest searchRequest = CardSearchRequest.builder()
				.fields("field1", "field2")
				.build();
		
		assertTrue(((String)searchRequest.get("fields")).contains("field1"));
		assertTrue(((String)searchRequest.get("fields")).contains("field2"));
		
	}	
	
	@Test
	public void searchByEmbedString() throws IOException {
		CardSearchRequest searchRequest = CardSearchRequest.builder()
				.embed("authorIds", "id")
				.build();
		
		assertTrue(((String)searchRequest.get("embed")).contains("authorIds"));
		assertTrue(((String)searchRequest.get("embed")).contains("id"));
		
	}
	
	@Test
	public void searchByAllFields() throws IOException {
//		@Key
//		private final String query;
//		@Key
//		private final CardStatus status;
//		@Key
//		private final String locale;
//		@Key
//		private final Integer limit;
//		@Key
//		private final Integer offset;
//		@Key
//		private final DateTime initDate;
//		@Key
//		private final DateTime endDate;
//		@Key
//		private final Integer maxAge;
//		@Key
//		private final String categories;
//		@Key
//		private final String metaTags;
//		@Key
//		private final String authorIds;
//		@Key
//		private final String communityIds;
//		@Key
//		private final String providerIds;
//		@Key
//		private final Double decayment;
//		@Key
//		private final String fields;
//		@Key
//		private final String embed;
		
		CardSearchRequest searchRequest = CardSearchRequest.builder()
				.query("CI&T")
				.status(CardStatus.APPROVED)
				.locale("campinas")
				.limit(10101)
				.offset(201102)				
				//initDate accept Long, Date, (Date, zone)++  * Look for Datetime.class to more info
				.initDate(new DateTime(12312L))
				.endDate(new DateTime("2011-05-03T11:58:01Z"))
				.build();
		System.out.println(searchRequest);
//		assertTrue(((String)searchRequest.get("initDate")).contains("2011-05-03T11:58:01Z"));
//		assertTrue(((String)searchRequest.get("embed")).contains("id"));
	
	}
}
