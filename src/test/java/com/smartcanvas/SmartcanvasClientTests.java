package com.smartcanvas;

import java.io.IOException;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.jose4j.lang.JoseException;
import org.junit.Test;

import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.smartcanvas.model.Author;
import com.smartcanvas.model.Card;
import com.smartcanvas.model.Community;
import com.smartcanvas.model.ContentProvider;
import com.smartcanvas.model.GetResponse;

public class SmartcanvasClientTests {

    static final HttpTransport HTTP_TRANSPORT = new NetHttpTransport();
    static final JsonFactory JSON_FACTORY = new JacksonFactory();
    private Smartcanvas smartcanvas;
    private static final String CLIENT_ID = "yYSr9igrmPkR";
    private static final String CLIENT_SECRET = "ce4a3f668a3d9ca30a6653a005f86b063906769bad7f27f1a83241c267028e89";

    
    public SmartcanvasClientTests() throws JoseException {
        smartcanvas = new Smartcanvas(HTTP_TRANSPORT, JSON_FACTORY, CLIENT_ID, CLIENT_SECRET);
    }

    @Test
    public void search() throws IOException {
        GetResponse response = smartcanvas.cards().search("teste");
        System.out.println(response);
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

        smartcanvas.cards().addCard(card); // Record the card
    }

    @Test
    public void addCategoriesandMeta() throws IOException {
        Card card = new Card(givenProvider());
        card.addCategories("GYM", "Walk4life");
        card.addMetaTags("Fixed");
        card.setTitle("Card Title Categories Example");
        card.setMnemonic("NikeShoes");
        card.setAutoApprove(true);
        smartcanvas.cards().addCard(card);
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

        smartcanvas.cards().addCard(card);
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
        DateTime expirationCard = DateTime.parse("30/12/2015 01:00:00",
                DateTimeFormat.forPattern("dd/MM/yyyy HH:mm:ss"));
        card.setPublishDate(publishCard);
        card.setExpirationDate(expirationCard);
        smartcanvas.cards().addCard(card);
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
        author.setId("email@example.com"); /*
                                            * Author identification in Smart
                                            * Canvas. That's a Person ID or
                                            * email. If not provided, this
                                            * author doesn't exist in Smart
                                            * Canvas (but it's still valid)
                                            */

        author.setDisplayName("Ci&T G. M."); /* Author display name */
        author.setImageURL("http://www.ciandt.com/ciandt/images/logo-larger.jpg"); /*
                                                                                    * Author
                                                                                    * image
                                                                                    * URL
                                                                                    */

        card.setAuthor(author);
        smartcanvas.cards().addCard(card);

    }

    @Test
    public void photoAttachment() throws IOException {
        Card card = new Card(givenProvider());
        card.setTitle("Attach Photo URL Example");
        card.setMnemonic("photo"); // Underscore not accept
        card.setSummary("Attachment Photo Test");
        card.setContent("Write the content of the card here");
        card.setAutoApprove(true);
        card.addCategories("junit", "java-sdk", "photo-attachment");
        card.addPhotoAttachment("https://lh5.googleusercontent.com/-ENfgdf0kzw0/UqckpA6C4NI/AAAAAAAABEg/hV-5RFJKPq4/w1600-h900-no/google-partner.png");
        smartcanvas.cards().addCard(card);
    }

    @Test
    public void shouldAddCardWithYoutubeVideoAttachment() throws IOException {
        Card card = new Card(givenProvider());
        card.setTitle("Attach Video Yotube URL Example");
        card.setMnemonic("Video");
        card.setSummary("Attachment Video Test");
        card.setContent("Write the content of the card here");
        card.setAutoApprove(true);
        card.addCategories("junit", "java-sdk", "youtube");
        card.addVideoAttachment("https://www.youtube.com/watch?v=3qbU8TUl2sU");
        smartcanvas.cards().addCard(card);
    }

    @Test
    public void shouldAddCardWithVimeoVideoAttachment() throws IOException {
        Card card = new Card(givenProvider());
        card.setTitle("Attach Video Vimeo URL Example");
        card.setMnemonic("Video");
        card.setSummary("Attachment Video Test");
        card.setContent("Write the content of the card here");
        card.setAutoApprove(true);
        card.addCategories("junit", "java-sdk", "vimeo");
        // FIXME checar o que deve ser preenchido para a imagem no card fechado
        card.addVideoAttachment("https://vimeo.com/133697756");
        smartcanvas.cards().addCard(card);
    }

    
    
    @Test
    public void shouldAddCardWithArticleAttachment() throws IOException {
    	Card card = new Card(givenProvider());
    	card.setTitle("Attach Article Example");
    	card.setMnemonic("Article");
    	card.setSummary("Attachment Article Summary");
    	card.setContent("Write the content of the card here");
    	card.setAutoApprove(true);
    	card.addCategories("article");
    	card.addArticleAttachment("https://www.google.com.br/design/articles", "http://angular.marketing/wp-content/uploads/google-in-depth-article-results.png");
    	
    	smartcanvas.cards().addCard(card);
    }
    
    
    private ContentProvider givenProvider() {
        return new ContentProvider("ID provider", "GooglePlus", "gmoneda");
    }
}


