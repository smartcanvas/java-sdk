package com.smartcanvas;

import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.DateTime;
import org.jose4j.lang.JoseException;
import org.junit.Test;

import java.io.IOException;

public class CardSearchIntegrationTest {
	
	private Smartcanvas smartcanvas;
	static final HttpTransport HTTP_TRANSPORT = new NetHttpTransport();
	static final JsonFactory JSON_FACTORY = new JacksonFactory();
	private static final String CLIENT_ID = "yYSr9igrmPkR";
	private static final String CLIENT_SECRET = "ce4a3f668a3d9ca30a6653a005f86b063906769bad7f27f1a83241c267028e89";

	public CardSearchIntegrationTest() throws JoseException {
		smartcanvas = new Smartcanvas(HTTP_TRANSPORT, JSON_FACTORY, CLIENT_ID, CLIENT_SECRET, true);
	}
	
	@Test
	public void SearchTest() throws IOException {
		CardSearchRequest searchRequest = CardSearchRequest.builder()
				.authorIds("fuechi@ciandt.com", "gmoneda@ciandt.com")
				.build();
		System.out.println(searchRequest);
		smartcanvas.cards().search(searchRequest);
	}

    @Test
    public void SearchTest2() throws IOException {
        CardSearchRequest searchRequest = CardSearchRequest.builder()
                .query("ONU")
                //.maxAge(2)
                .initDate(new DateTime("2015-01-01"))
                .authorIds("fuechi@ciandt.com", "gmoneda@ciandt.com")
                .build();
        System.out.print(searchRequest);
        smartcanvas.cards().search(searchRequest);
    }
	
	
	
	
	
	
}