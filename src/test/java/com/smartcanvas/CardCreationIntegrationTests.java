package com.smartcanvas;

import com.google.api.client.util.DateTime;
import com.smartcanvas.model.Card;
import com.smartcanvas.model.Card.Author;
import com.smartcanvas.model.Card.Community;
import com.smartcanvas.model.Card.ContentProvider;
import com.smartcanvas.model.CardId;
import com.smartcanvas.model.Attachment;

import org.jose4j.lang.JoseException;
import org.junit.Test;

import java.io.IOException;
import java.util.UUID;

import static org.junit.Assert.assertNotNull;

public class CardCreationIntegrationTests extends AbstractSmartCanvasIntegrationTests {


    public CardCreationIntegrationTests() throws JoseException {
        super();
    }

	@Test
	public void testCardBuilder() throws IOException {
		Author author = new Author();
		author.setDisplayName("afsd");
		author.setId("aa");

		Card buildCard;
		buildCard = Card.newBuilder()
				.withContentProvider(givenProvider())
				.withMnemonic("CardBuilder")
				.withTitle("Title build CArd")
				.withMetaTags("ola", "mundo", "d1", "gmoneda", "teste", "metaTag")
				.withCategories("Categorie 1", "categorie 2", "Cate")
				.withPhotoAttachment("https://lh5.googleusercontent.com/-ENfgdf0kzw0/UqckpA6C4NI/AAAAAAAABEg/hV-5RFJKPq4/w1600-h900-no/google-partner.png")
//				.withVideoAttachment("https://www.youtube.com/watch?v=3qbU8TUl2sU")

				.build();
		smartcanvas.cards().insert(buildCard);
	}

	@Test
	public void testCardBuilderUpdate() throws IOException {
		Card buildCard = Card.newBuilder()
				.withContentProvider(givenProvider())
				.withMnemonic("CardBuilder")
				.withTitle("Title build CArd")
				.build();
		smartcanvas.cards().insert(buildCard);
	}

    @Test
    public void getById() throws IOException {
        Card card = smartcanvas.cards().get(6003583740805120l);
        assertNotNull(card);
    }
	
    @Test
	public void addSimpleCard() throws IOException {
		Card card = new Card(givenProvider());
//		card.addMetaTags("leo");
		card.setAutoApprove(true);
		
		CardId id = smartcanvas.cards().insert(card);
        assertNotNull(id);
	}

   @Test
    public void addCardWithContentProvider() throws IOException {
        Card card = new Card(givenProvider());
        card.setTitle("Card Title");
        card.setSummary("This is the summary");
        card.setContent("Write the content of the card here");
        card.setAutoApprove(true);
        smartcanvas.cards().insert(card);
    }
	   
	@Test
	public void addCategoriesandMeta() throws IOException {
		Card card = new Card(givenProvider());
		card.addCategories("GYM", "Walk4life");
//		card.addMetaTags("Fixed");
		card.setTitle("Card Title Categories Example");
		card.setAutoApprove(true);
		smartcanvas.cards().insert(card);
	}

	@Test
	public void addCommunity() throws IOException {
		Card card = new Card(givenProvider());

		card.setTitle("Card Title ");

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
		card.setSummary("Date Test");
		card.setContent("Write the content of the card here");
		card.setAutoApprove(true);
		card.setPublishDate(DateTime.parseRfc3339("2015-12-31"));
		card.setExpirationDate(DateTime.parseRfc3339("2015-12-31"));
		smartcanvas.cards().insert(card);
	}

	@Test
	public void addAuthor() throws IOException {
		Card card = new Card(givenProvider());
		card.setTitle("Image URL Example");
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
		card.setSummary("Attachment Photo Test");
		card.setContent("Write the content of the card here");
		card.setAutoApprove(true);
		card.addCategories("junit", "java-sdk", "photo-attachment");
//		card.addPhotoAttachment(
//                "https://lh5.googleusercontent.com/-ENfgdf0kzw0/UqckpA6C4NI/AAAAAAAABEg/hV-5RFJKPq4/w1600-h900-no/google-partner.png");
		smartcanvas.cards().insert(card);
	}

	@Test
	public void shouldAddCardWithYoutubeVideoAttachment() throws IOException {
		Card card = new Card(givenProvider());
		card.setTitle("Attach Video Yotube URL Example");
		card.setSummary("Attachment Video Test");
		card.setContent("Write the content of the card here");
		card.setAutoApprove(true);
		card.addCategories("junit", "java-sdk", "youtube");
//		card.addVideoAttachment("https://www.youtube.com/watch?v=3qbU8TUl2sU");
		smartcanvas.cards().insert(card);
	}

	@Test
	public void shouldAddCardWithVimeoVideoAttachment() throws IOException {
		Card card = new Card(givenProvider());
		card.setTitle("Attach Video Vimeo URL Example");
		card.setSummary("Attachment Video Test");
		card.setContent("Write the content of the card here");
		card.setAutoApprove(true);
		card.addCategories("junit", "java-sdk", "vimeo");
		// FIXME checar o que deve ser preenchido para a imagem no card fechado
//		card.addVideoAttachment("https://vimeo.com/133697756");
		smartcanvas.cards().insert(card);
	}

	@Test
	public void shouldAddCardWithArticleAttachment() throws IOException {
		Card card = new Card(givenProvider());
		card.setTitle("Attach Article Example");
		card.setSummary("Attachment Article Summary");
		card.setContent("Write the content of the card here");
		card.setAutoApprove(true);
		card.addCategories("article");
//		card.addArticleAttachment("https://www.google.com.br/design/articles",
//                "http://angular.marketing/wp-content/uploads/google-in-depth-article-results.png");

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
		card.setSummary("~~~~~~Article Summary updated~~~ ");
		card.setContent("Write the content of the card here");
		card.setAutoApprove(true);
		card.addCategories("novacategoria");
//		card.addArticleAttachment("https://www.google.com.br/design/articles",
//                "http://angular.marketing/wp-content/uploads/google-in-depth-article-results.png");

		String id = "6087103192498176";
		smartcanvas.cards().update(card, id);

	}

    @Test
    public void addJsonExtendedData() throws IOException {
        Card card = new Card(givenProvider());
        card.setTitle("Card Title ");
        card.setSummary("Community Test");
        card.setContent("Write the content of the card here");
        card.setAutoApprove(true);
        JsonExtendedData jsonExtendedData = new JsonExtendedData("fabito", "campinas", "Brasil", "www.google.com", "123124", "http://www.keenthemes.com/preview/metronic/theme/assets/global/plugins/jcrop/demos/demo_files/image1.jpg ", "Test User", "CEO", "This is a test company", "UN", "ISO9002", "1 July, 2015", 100, true, 2, 2);
        card.setJsonExtendedData(jsonExtendedData);
        smartcanvas.cards().insert(card);
    }

	private ContentProvider givenProvider() {
	    UUID uuid = UUID.randomUUID();
		System.out.println(uuid.toString());
		return new ContentProvider("java-sdk-unit-tests", uuid.toString(), "gmoneda");

	}
}