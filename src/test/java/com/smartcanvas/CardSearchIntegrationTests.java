package com.smartcanvas;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

import java.io.IOException;
import java.util.List;

import org.jose4j.lang.JoseException;
import org.junit.Ignore;
import org.junit.Test;

import com.github.javafaker.Faker;
import com.smartcanvas.CardSearchRequest.CardSearchRequestBuilder;
import com.smartcanvas.model.Card;
import com.smartcanvas.model.CardId;
import com.smartcanvas.model.CardSearchResult;

public class CardSearchIntegrationTests extends AbstractSmartCanvasIntegrationTests {

    private static final Faker faker = new Faker();

    public CardSearchIntegrationTests() throws JoseException {
        super();
    }

    @Test
    @Ignore
    public void createCards() throws IOException {
        String lorempixel = "lorempixel";
        
        for (int i = 0; i < 20; i++) {
            Card card = new Card();
            card.setTitle(faker.lorem().sentence());
            card.setContent(paragraphsToString(faker.lorem().paragraphs(5)));
            card.addPhotoAttachment("http://lorempixel.com/1920/1080/");
            card.addCategories(lorempixel);
            card.setAutoApprove(true);
            CardId id = smartcanvas.cards().insert(card);
        }
    }

    // @Test
    // public void getAndUpdate() throws IOException {
    // long id = 4728327218659328l;
    // Card c = smartcanvas.cards().get(id);
    // c.addCategories("atualizei");
    // c.setSummary(faker.lorem().sentence());
    // CardId cid = smartcanvas.cards().update(id, c);
    // }

    private String paragraphsToString(List<String> paragraphs) {
        StringBuilder b = new StringBuilder();
        for (String string : paragraphs) {
            b.append(string);
        }
        return b.toString();
    }

    @Test
    public void simpleSearch() throws IOException {
        // String lorempixel = "lorempixel";
        //
        // Card card = new Card();
        // card.setMnemonic("facilis-a-ut-odio-mollitia-nisi");
        // card.setTitle(faker.lorem().sentence());
        // card.setContent(faker.lorem().paragraph());
        //
        // //curl http://uifaces.com/api/v1/random
        // //curl https://randomuser.me/api/
        // //card.author( faker.name().name(), faker.internet().emailAddress() )
        // card.addPhotoAttachment("http://lorempixel.com/1920/1080/");
        // card.addCategories(lorempixel);
        // card.setAutoApprove(true);
        //
        // CardId id = smartcanvas.cards().insert(card);

        CardSearchRequest req = builder().categories("lorempixel")

        // .query("fun")
        // .authorIds("fuechi@ciandt.com")
        // .fields("id", "mnemonic")
        // .maxAge(7)
                .build();
        CardSearchResult result = smartcanvas.cards().search(req);
        System.out.println(result.getMeta());
        System.out.println(result.cards());
    }

    private CardSearchRequestBuilder builder() {
        return CardSearchRequest.builder(true);
    }

}
