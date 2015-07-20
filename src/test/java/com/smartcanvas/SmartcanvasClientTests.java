package com.smartcanvas;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.junit.Before;
import org.junit.Test;

import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.smartcanvas.model.Attachment;
import com.smartcanvas.model.Attachment.TypeEnum;
import com.smartcanvas.model.Author;
import com.smartcanvas.model.Card;
import com.smartcanvas.model.Community;
import com.smartcanvas.model.ContentProvider;
import com.smartcanvas.model.Image;

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
    public void photoAttachment() throws IOException {
        Card card = new Card(givenProvider());
        card.setTitle("Attach Photo URL Example");
        card.setMnemonic("photo"); // Underscore not accept
        card.setSummary("Attachment Photo Test");
        card.setContent("Write the content of the card here");
        card.setAutoApprove(true);
    
        listAttach = new ArrayList<Attachment>(); 				// Create List of Attachment
        
        Attachment attachment = new Attachment();				
   
        // attachment.set...(value)
        attachment.setType(TypeEnum.PHOTO); 				  	// Type of Attachment: {PHOTO,  ARTICLE,  VIDEO,  DRIVE } UPPERCASE
         
        attachment.setDisplayName("Attachment Name");
        attachment.setContentURL("Content URL for this attachment ");     	
        attachment.setJsonExtendedData("JSON data with extra information saved with this attachment");
        
        List<Image> imageList = new ArrayList<>(); 				// Create List of Images
        Image image = contentPhoto();
        imageList.add(image);
        attachment.setImages(imageList);
		listAttach.add(attachment);
		
		card.setAttachments(listAttach);
        smartcanvas.addCard(card);
        
    }
    
    @Test
    public void videoAttachment() throws IOException {
        Card card = new Card(givenProvider());
        card.setTitle("Attach Video URL Example");
        card.setMnemonic("Video");
        card.setSummary("Attachment Video Test");
        card.setContent("Write the content of the card here");
        card.setAutoApprove(true);
    
        listAttach = new ArrayList<Attachment>(); 				// Create List of Attachment
        
        Attachment attachment = new Attachment();				
   
        // attachment.set...(value)
        attachment.setType(TypeEnum.VIDEO); 	//  Type of Attachment: {PHOTO,  ARTICLE,  VIDEO,  DRIVE } (UPPERCASE VALUE)
        
        attachment.setDisplayName("Attachment Name");
        attachment.setContentURL("Content URL for this attachment ");     	
        attachment.setJsonExtendedData("JSON data with extra information saved with this attachment");
        
        List<Image> imageList = new ArrayList<>(); 				
        Image image = contentVideo();
        imageList.add(image);
        attachment.setImages(imageList);
		listAttach.add(attachment);
		
		card.setAttachments(listAttach);
        smartcanvas.addCard(card);
        
    }
    
    
    
    
    
    //image.set...()
//    private Image contentPhoto(){
//    	Image image = new Image();
//    			return image.setUrl("https://lh4.googleusercontent.com/-bNQUAXKKh1U/AAAAAAAAAAI/AAAAAAAAAMU/MnC2eoG4Yjk/photo.jpg").setType("photo").setHeight(50).setWidth(50).setOriginalURL("https://lh4.googleusercontent.com/-bNQUAXKKh1U/AAAAAAAAAAI/AAAAAAAAAMU/MnC2eoG4Yjk/photo.jpg");
//    			
//    }
//    
    private Image contentPhoto(){
    	Image image = new Image();
    	image.setType("tipo");
    	image.setHeight(300);
    	image.setWidth(600);
    	image.setOriginalURL("https://lh5.googleusercontent.com/-ENfgdf0kzw0/UqckpA6C4NI/AAAAAAAABEg/hV-5RFJKPq4/w1600-h900-no/google-partner.png");
    	return image;
    }
    
    
    //image.set...()
    private Image contentVideo(){
    	Image image = new Image();
    			return image.setUrl("https://www.youtube.com/watch?v=Qs_NlNJwBe8").setType("video").setHeight(300).setWidth(200).setOriginalURL("https://www.youtube.com/watch?v=Qs_NlNJwBe8");
    			
    			
    			
    
    }

    
 

      
    private ContentProvider givenProvider() {
        return new ContentProvider("ID provider", "GooglePlus", "gmoneda");
    }
}    
//    private Image givenImage(){
//    	return givenImage(300, 600, "https://avatars3.githubusercontent.com/u/1983873", "image/jpg");
//    }
//    private Image givenImage(int height, int width, String originalURL, String mimeType) {
//        Image image = new Image();
//        image.setHeight(height);
//        image.setWidth(width);
//        image.setOriginalURL(originalURL);
//        image.setType(mimeType);
//        return image;
//    }
//    
//    @Test
//    public void shouldPopulatePhotoAttachments() {
//        Author author = givenAuthor();
//        Card input = givenCard(author, true);        
//        Attachment attachment = givenPhotoAttachment();
//        input.setAttachments(Lists.newArrayList(attachment));
//        Card output = SmartcanvasClient.toDomainModel(input);
//        assertEquals(input.getAttachments().size(), output.getAttachments().size());
//        assertIsPhotoAttachment(output.getAttachments().get(0));
//    }
//    
//    private Author givenAuthor() {
//        return givenAuthor("author@sc.com", "Author", "http://sc.com/myavatar.jpg");
//    }
//    
//    private Author givenAuthor(String id, String displayName, String avatar) {
//        Author author = new Author();
//        author.setId(id);
//        author.setDisplayName(displayName);
//        author.setImageURL(avatar);
//        return author;
//    }
//    
//    private Card givenCard() {
//        return givenCard(givenAuthor());
//    }    
//    
//    private Card givenCard(Author author) {
//        return givenCard(author, null);
//    }
//
//    private Card givenCard(Author author, Boolean autoApprove) {
//        Card card = new Card(null);
//        card.setAuthor(author);
//        card.setAutoApprove(autoApprove );
//        card.setContentProvider(givenProvider());
//        return card;
//    }
//    
//    private Attachment givenPhotoAttachment() {
//        Attachment attachment = new Attachment();
//        attachment.setType(TypeEnum.PHOTO);
//        Image image = givenImage();
//        attachment.setImages(Lists.newArrayList(image);
//        return attachment;
//    }
//
//}