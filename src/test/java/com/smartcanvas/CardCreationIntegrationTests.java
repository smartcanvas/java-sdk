package com.smartcanvas;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotEquals;

import java.io.IOException;

import org.jose4j.lang.JoseException;
import org.junit.Test;

import com.google.api.client.util.DateTime;
import com.smartcanvas.model.Card;
import com.smartcanvas.model.Card.Author;
import com.smartcanvas.model.Card.Community;
import com.smartcanvas.model.Card.ContentProvider;
import com.smartcanvas.model.CardId;

public class CardCreationIntegrationTests extends AbstractSmartCanvasIntegrationTests {

	public CardCreationIntegrationTests() throws JoseException {
        super();
    }

    @Test
    public void getById() throws IOException {
        Card card = smartcanvas.cards().get(6003583740805120l);
        assertNotNull(card);
    }
	
    @Test
	public void addSimpleCard() throws IOException {
		Card card = new Card(givenProvider());
		card.setMnemonic("upa-upa-upa-cavalinho-alazao");
		card.addMetaTags("leo");
		card.setAutoApprove(true);
		
		CardId id = smartcanvas.cards().insert(card);
		
		card.setContentProvider(givenProvider());
		card.setMnemonic("upa-upa-upa-cavalinho-alazaozao");
		
		CardId id2 = smartcanvas.cards().insert(card);

        assertNotNull(id);
        assertNotNull(id2);
        
        assertNotEquals(id, id2);
        
	}

   @Test
    public void addCardWithContentProvider() throws IOException {
        Card card = new Card(givenProvider());
        card.setTitle("Card Title");
        card.setMnemonic("Memes");
        card.setSummary("This is the summary");
        card.setContent("Write the content of the card here");
        card.setAutoApprove(true);
        smartcanvas.cards().insert(card);
    }
	   
	@Test
	public void addCategoriesandMeta() throws IOException {
		Card card = new Card(givenProvider());
		card.addCategories("GYM", "Walk4life");
		card.addMetaTags("Fixed");
		card.setTitle("Card Title Categories Example");
		card.setMnemonic("NikeShoes");
		card.setAutoApprove(true);
		smartcanvas.cards().insert(card);
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
		comunities.setDisplayName("JEDI GROUP");
		comunities.setId("Community");
		card.setCommunity(comunities);

		smartcanvas.cards().insert(card);
	}

	@Test
	public void addDateTime() throws IOException {
		Card card = new Card(givenProvider());
		card.setTitle("Card Title Date Example");
		card.setMnemonic("DateTest");
		card.setSummary("Date Test");
		card.setContent("Write the content of the card here");
		card.setAutoApprove(true);
		card.setPublishDate(DateTime.parseRfc3339("2010-07-17"));
		card.setExpirationDate(DateTime.parseRfc3339("2015-12-30"));
		smartcanvas.cards().insert(card);
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
		/*
		 * Author identification in Smart Canvas. That's a Person ID or email.
		 * If not provided, this author doesn't exist in Smart Canvas (but it's
		 * still valid)
		 */
		author.setId("email@example.com");
		/* Author display name */
		author.setDisplayName("Ci&T G. M.");
		/* Author image URL */
		author.setImageURL("http://www.ciandt.com/ciandt/images/logo-larger.jpg");

		card.setAuthor(author);
		smartcanvas.cards().insert(card);

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
		card.addPhotoAttachment(
				"https://lh5.googleusercontent.com/-ENfgdf0kzw0/UqckpA6C4NI/AAAAAAAABEg/hV-5RFJKPq4/w1600-h900-no/google-partner.png");
		smartcanvas.cards().insert(card);
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
		smartcanvas.cards().insert(card);
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
		smartcanvas.cards().insert(card);
	}

	@Test
	public void shouldAddCardWithArticleAttachment() throws IOException {
		Card card = new Card(givenProvider());
		card.setTitle("Attach Article Example");
		card.setMnemonic("articleorigin");
		card.setSummary("Attachment Article Summary");
		card.setContent("Write the content of the card here");
		card.setAutoApprove(true);
		card.addCategories("article");
		card.addArticleAttachment("https://www.google.com.br/design/articles",
				"http://angular.marketing/wp-content/uploads/google-in-depth-article-results.png");

		CardId response = smartcanvas.cards().insert(card);

		assertNotNull(response);
		assertNotNull(response.id());
		assertNotNull(response.mnemonic());

		System.out.println(response);
	}

	@Test
	public void updateCard() throws IOException {
		Card card = new Card(givenProvider());
		card.setTitle("Attach Article Example");
		card.setMnemonic("articleUpdate");
		card.setSummary("~~~~~~Article Summary updated~~~ ");
		card.setContent("Write the content of the card here");
		card.setAutoApprove(true);
		card.addCategories("novacategoria");
		card.addArticleAttachment("https://www.google.com.br/design/articles",
				"http://angular.marketing/wp-content/uploads/google-in-depth-article-results.png");

		String id = "6087103192498176";
		smartcanvas.cards().update(card, id);

	}

	// @Test
	// public void deleteCard() throws IOException {
	// Card card = new Card(givenProvider());
	// card.setAutoApprove(true);
	//
	// //Mnemonic or ID card
	// String id = "5233755344076800";
	// smartcanvas.cards().delete(id);
	//
	//
	// }

	private ContentProvider givenProvider() {
		return new ContentProvider("ID provider", "GooglePlus", "gmoneda");
	}
}