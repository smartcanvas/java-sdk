package com.smartcanvas;

import java.io.IOException;
import java.util.List;

import org.joda.time.DateTime;

import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.junit.Before;
import org.junit.Test;

import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.Key;
import com.smartcanvas.SmartcanvasClient;
import com.smartcanvas.model.Attachment;
import com.smartcanvas.model.Attachment.TypeEnum;
import com.smartcanvas.model.Author;
import com.smartcanvas.model.Card;
import com.smartcanvas.model.Community;
import com.smartcanvas.model.ContentProvider;
import com.smartcanvas.model.Image;
import com.sun.xml.internal.ws.policy.sourcemodel.ModelNode.Type;

import java.util.ArrayList;

public class SmartcanvasClientTests {

    static final HttpTransport HTTP_TRANSPORT = new NetHttpTransport();
    static final JsonFactory JSON_FACTORY = new JacksonFactory();
    private SmartcanvasClient smartcanvas;
    private static final String CLIENT_ID = "yYSr9igrmPkR";
    private List<String> list, mTag;
    private List<Attachment> listAttach;

    @Before
    public void setUp() {
        smartcanvas = new SmartcanvasClient(HTTP_TRANSPORT, JSON_FACTORY, CLIENT_ID);
    }

    @Test
    public void addSimpleCard() throws IOException {
        Card card = new Card(givenProvider());
        card.setTitle("Card Title");
        card.setMnemonic("Meme"); // URL Mnemonic
        card.setSummary("This is the summary");
        card.setContent("Write the content of the card here");
        card.setAutoApprove(true); /*
                                    * 'true': No moderation; 'false': There is a
                                    * Moderation; The default is FALSE.
                                    */

        smartcanvas.addCard(card); // Record the card
    }

    @Test
    public void addCategoriesandMeta() throws IOException {

        list = new ArrayList<String>();
        list.add("GYM"); // Categories (tags) associated to this card"
        list.add("Walk4life");

        mTag = new ArrayList<String>();
        mTag.add("Fixed"); // Meta-tags are intented to be used by system / API
                           // only, not visible for end-users.

        Card card = new Card(givenProvider());
        card.setCategories(list);
        card.setMetaTags(mTag);
        card.setTitle("Card Title Categories Example");
        card.setMnemonic("NikeShoes");
        card.setAutoApprove(true);

        smartcanvas.addCard(card);

    }

    @Test
    public void addCommunity() throws IOException {
        Card card = new Card(givenProvider());
        card.setTitle("Card Title ");
        card.setMnemonic("Community");
        card.setSummary("Community Test");
        card.setContent("Write the content of the card here");
        card.setAutoApprove(true);

        Community comunities = new Community();
        comunities.setDisplayName("JEDI GROUP"); // User-friendly community
                                                 // display name
        comunities.setId("Community"); // Community ID or mnemonic
        card.setCommunity(comunities);

        smartcanvas.addCard(card);
    }

    @Test
    public void addDateTime() throws IOException {
        Card card = new Card(givenProvider());
        card.setTitle("Card Title Date Example");
        card.setMnemonic("DateTest");
        card.setSummary("Date Test");
        card.setContent("Write the content of the card here");
        card.setAutoApprove(true);

        DateTime publishCard = DateTime.parse("17/07/2015 20:27:05", DateTimeFormat.forPattern("dd/MM/yyyy HH:mm:ss"));
        DateTime expirationCard = DateTime.parse("30/12/2015 01:00:00", DateTimeFormat.forPattern("dd/MM/yyyy HH:mm:ss"));
        card.setPublishDate(publishCard);
        card.setExpirationDate(expirationCard);
        smartcanvas.addCard(card);
    }
    
    
    
    @Test
    public void addAuthor() throws IOException {
        Card card = new Card(givenProvider());
        card.setTitle("Image URL Example");
        card.setMnemonic("AuthorUrl");
        card.setSummary("Author Test");
        card.setContent("Write the content of the card here");
        card.setAutoApprove(true);
    
        
        Author author = new Author();
        author.setId("email@example.com");				/* Author identification in Smart Canvas. 
        												That's a Person ID or email. If not provided, this author doesn't exist in Smart Canvas (but it's still valid) */
        
        author.setDisplayName("Ci&T G. M.");			/* Author display name */
        author.setImageURL("http://www.ciandt.com/ciandt/images/logo-larger.jpg"); 					/* Author image URL */
        
        card.setAuthor(author);
        System.out.println(card);

        smartcanvas.addCard(card);

    }

    @Test
    public void addAttachment() throws IOException {
        Card card = new Card(givenProvider());
        card.setTitle("Attach URL Example");
        card.setMnemonic("Attachment");
        card.setSummary("Attachment Test");
        card.setContent("Write the content of the card here");
        card.setAutoApprove(true);
    
        listAttach = new ArrayList<Attachment>(); 				// Create List of Attachment
        
        Attachment attachment = new Attachment();				
   
        // attachment.set...(value)
        
        attachment.setType(TypeEnum.PHOTO); 				  	// Type of Attachment: {photo,  article,  video,  drive }
        
        attachment.setDisplayName("Attachment Name");
        attachment.setContentURL("Content URL for this attachment ");     	
        attachment.setJsonExtendedData("JSON data with extra information saved with this attachment");
        
        List<Image> imageList = new ArrayList<>(); 				// Create List of Images
        Image image = new Image();
        
        // image.set...();
        image.setUrl("http://www.ciandt.com/ciandt/images/logo-larger.jpg");   // URL IMAGE
        image.setType("Image Type");
        image.setHeight(200); 													// Image height
        image.setWidth(400);													// Image width
        image.setOriginalURL("http://www.ciandt.com/us-en/");					// Original image URL on the content provider
        imageList.add(image);
        attachment.setImages(imageList);
		listAttach.add(attachment);
		
		card.setAttachments(listAttach);
		System.out.println(card);
        smartcanvas.addCard(card);
        
    } 
      
    private ContentProvider givenProvider() {
        return new ContentProvider("ID provider", "GooglePlus", "gmoneda");
    }

}