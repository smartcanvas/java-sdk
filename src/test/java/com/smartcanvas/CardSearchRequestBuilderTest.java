package com.smartcanvas;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;

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

}
