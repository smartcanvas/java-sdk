package com.smartcanvas;

import com.google.api.client.util.DateTime;
import com.smartcanvas.model.Card;
import com.smartcanvas.model.Card.Author;
import com.smartcanvas.model.Card.ContentProvider;
import com.smartcanvas.model.CardId;
import com.smartcanvas.model.JsonExtendedData;
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
	public void addSimpleCardBuilder() throws IOException {
		Card buildCard = Card.newBuilder()
				.withContentProvider(givenProvider())
				.withTitle("Simple Card Title")
				.withMnemonic("card3")
				.withAutoApprove(true)
				.build();
		smartcanvas.cards().insert(buildCard);
	}

	@Test
	public void addCommunityCardBuilder() throws IOException {
		Card buildCard = Card.newBuilder()
				.withContentProvider(givenProvider())
				.withTitle("Simple Card Title")
				.withMnemonic("communityCardTest")
//				.withCommunity("idCommunity", "displayName")
				.withAutoApprove(true)
				.build();
		smartcanvas.cards().insert(buildCard);
	}
	   
	@Test
	public void addCategoriesandMetaTagBuilder() throws IOException {
		Card buildCard = Card.newBuilder()
				.withContentProvider(givenProvider())
				.withTitle("Categories and metaTag tests")
				.withMnemonic("CategoriesmetaTags")
				.withMetaTags("FIXED")
				.withCategories("Category 1", "Category 2", "Categories and more and more")
				.build();
		smartcanvas.cards().insert(buildCard);
	}

	@Test
	public void addDateTimeBuilder() throws IOException {
		Card buildCard = Card.newBuilder()
				.withContentProvider(givenProvider())
				.withTitle("Published and Expiration Card Data")
				.withMnemonic("dateTime")
				.withPublishDate(new DateTime("2015-01-25"))
				.withExpirationDate(new DateTime("2016-01-25"))
				.build();
		smartcanvas.cards().insert(buildCard);
	}

	/* There is two ways to create a Card, you can do this with builder or can instance of a object */
	/* Follow a example to create a Card with author of two ways*/

	@Test
	public void addAuthorBuilder() throws IOException {
		Card buildCard = Card.newBuilder()
				.withContentProvider(givenProvider())
				.withTitle("Author Card Test")
				.withAuthor("authorID", "authorName")
				.withAutoApprove(true)
				.build();
		smartcanvas.cards().insert(buildCard);
	}

	@Test
	public void addAuthor() throws IOException {
		Card card = new Card(givenProvider());
		card.setTitle("Image URL Example");
		card.setSummary("Author Test");
		card.setContent("Write the content of the card here");
		card.setAutoApprove(true);

		Author author = new Author();
		author.setId("email@example.com");
		author.setImageURL("http://www.ciandt.com/ciandt/images/logo-larger.jpg");
		card.setAuthor(author);
		smartcanvas.cards().insert(card);
	}
	@Test
	public void AddCardWithYoutubeVideoAttachment() throws IOException {
		Card buildCard = Card.newBuilder()
				.withContentProvider(givenProvider())
				.withTitle("SmartCanvas - Mars Cyrillo")
				.withMnemonic("youtubeVideo")
				.withVideoAttachment("https://www.youtube.com/watch?v=h5peff8v7xw")
				.withAutoApprove(true)
				.build();
		System.out.println(buildCard);
		smartcanvas.cards().insert(buildCard);
	}

	@Test
	public void shouldAddCardWithVimeoVideoAttachment() throws IOException {
		Card buildCard = Card.newBuilder()
				.withContentProvider(givenProvider())
				.withTitle("Vimeo Video Test")
				.withMnemonic("VimeoVideo")
				.withVideoAttachment("https://vimeo.com/133697756")
				.withContent("Write the content of the card here")
				.withSummary("This is a summary test")
				.withAutoApprove(true)
				.build();
		smartcanvas.cards().insert(buildCard);
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
		smartcanvas.cards().update(card, id, "https://api-sandbox.smartcanvas.com/");
		System.out.println(card);
	}

    @Test
    public void addJsonExtendedData() throws IOException {

        Card card = new Card(givenProvider());
        card.setTitle("Card Title ");
        card.setSummary("JsonExtendedData Test");
        card.setContent("Write the content of the card here");
        card.setAutoApprove(true);
        JsonExtendedData jsonExtendedData = new JsonExtendedData("gmoneda", "campinas", "Brasil", "www.google.com", "123124", "http://www.keenthemes.com/preview/metronic/theme/assets/global/plugins/jcrop/demos/demo_files/image1.jpg ", "Test User", "CEO", "This is a test company", "UN", "ISO9002", "1 July, 2015", 100, true, 2, 2);
        card.setJsonExtendedData(jsonExtendedData);
        smartcanvas.cards().insert(card);
    }

	@Test
	public void addJsonExtendedDatawithBuilder() throws IOException {
		JsonExtendedData obj = new JsonExtendedData("gmoneda", "campinas", "Brasil", "www.google.com", "123124", "http://www.keenthemes.com/preview/metronic/theme/assets/global/plugins/jcrop/demos/demo_files/image1.jpg ", "Test User", "CEO", "This is a test company", "UN", "ISO9002", "1 July, 2015", 100, true, 2, 2);

		Card buildCard = Card.newBuilder()
				.withContentProvider(givenProvider())
				.withTitle("JsonExtendedData Builder Test")
				.withMnemonic("directcard")
				.withContent("Write the content of the card here")
				.withSummary("This is a summary test")
				.withAutoApprove(true)
				.withJsonExtendedData(obj)
				.build();

		CardId response = smartcanvas.cards().insert(buildCard);

		assertNotNull(response);
		assertNotNull(response.id());
		assertNotNull(response.mnemonic());

		System.out.println(response);
	}


	private ContentProvider givenProvider() {
	    UUID uuid = UUID.randomUUID();
		return new ContentProvider("java-sdk-unit-tests", uuid.toString(), "gmoneda");

	}
}