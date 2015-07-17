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
import com.smartcanvas.model.Community;
import com.smartcanvas.model.ContentProvider;

import java.util.ArrayList;

public class SmartcanvasClientTests {

	static final HttpTransport HTTP_TRANSPORT = new NetHttpTransport();
	static final JsonFactory JSON_FACTORY = new JacksonFactory();
	private SmartcanvasClient smartcanvas;
	private static final String CLIENT_ID = "yYSr9igrmPkR";
	private List<String> list, mTag;
	

	@Before
	public void setUp() {
		smartcanvas = new SmartcanvasClient(HTTP_TRANSPORT, JSON_FACTORY, CLIENT_ID);
	}

	@Test
	public void addSimpleCard() throws IOException {
		Card card = new Card(givenProvider());
		card.setTitle("Card Title");
		card.setMnemonic("Meme"); 									//URL Mnemonic
		card.setSummary("This is the summary");
		card.setContent("Write the content of the card here"); 
		card.setAutoApprove(true); 							/*		'true': No moderation; 
																	'false': There is a Moderation; 	
																	The default is FALSE.					*/
		
		smartcanvas.addCard(card); //Record the card 
	}

	@Test
	public void addCategoriesandMeta() throws IOException {

		list = new ArrayList<String>();
		list.add("GYM"); 									//Categories (tags) associated to this card"
		list.add("Walk4life"); 
		
		mTag = new ArrayList<String>(); 
		mTag.add("Fixed");									// Meta-tags are intented to be used by system / API only, not visible for end-users.
		
		
		Card card = new Card(givenProvider());
		card.setCategories(list); 
		card.setMetaTags(mTag);
		card.setTitle("Card Title");
		card.setMnemonic("NikeShoes");
		card.setAutoApprove(true);
		 
		smartcanvas.addCard(card);

	}

	@Test
	public void addCommunity() throws IOException {
		Card card = new Card(givenProvider());
		card.setTitle("Card Title");
		card.setMnemonic("Community"); 
		card.setSummary("This is the summary");
		card.setContent("Write the content of the card here"); 
		card.setAutoApprove(true);
		
		Community comunities = new Community();
		comunities.setDisplayName("JEDI GROUP"); 				//User-friendly community display name
		comunities.setId("Community"); 							//Community ID or mnemonic
		card.setCommunity(comunities);
		
		smartcanvas.addCard(card);
	}
	
	@Test
	public void createDateTime() throws IOException {
		
			
		
		Card card = new Card(givenProvider());
		card.setTitle("Card Title");
		card.setMnemonic("Community");
		card.setSummary("This is the summary");
		card.setContent("Write the content of the card here"); 
		card.setAutoApprove(true);
		
		card.setCreateDate(21354L);
		card.setExpirationDate(123L);
		card.setPublishDate(123L);
		card.setUpdateDate(1234L);
		
		smartcanvas.addCard(card);
	}
	
	
	
	
	
	
	
	private ContentProvider givenProvider() {
		return new ContentProvider("ID provider", "GooglePlus", "gmoneda");
	}

}