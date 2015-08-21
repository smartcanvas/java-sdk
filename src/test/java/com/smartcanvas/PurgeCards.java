package com.smartcanvas;

import com.google.api.client.util.DateTime;
import com.smartcanvas.model.Card;
import com.smartcanvas.model.CardSearchResult;
import com.smartcanvas.model.JsonExtendedData;
import org.jose4j.lang.JoseException;
import org.junit.Test;

import java.io.IOException;

public class PurgeCards extends AbstractSmartCanvasIntegrationTests {

    public PurgeCards() throws JoseException {
        super();
    }

    @Test
    public void purgeCards() throws IOException {
        /* Using a Object to search by jsonExtendedData or comment here and use a String call in .jsonExtendedData below*/
        JsonExtendedData obj = new JsonExtendedData("fabito", null, null, null, null, null, null,null, null, null,null, null, null,null, null, null);
       // System.out.println("obj: "+JSON_FACTORY.toString(obj));

        CardSearchRequest search = CardSearchRequest.builder("https://api.smartcanvas.com/")
//                .maxAge(2)
//               .initDate(new DateTime("1972-07-15"))
                .query("mars")
//                .status(Card.CardStatus.APPROVED)
//                .fields("id", "mnemonic")
                .limit(100)
                .offset(1)
                /* Use a String to search jsonExtendedData */
                //.jsonExtendedData("{\"name\":\"alan\",\"address\":\"campinas\"}")
               // .jsonExtendedData(obj)
                .build();


        System.out.println("Search: "+search);
        CardSearchResult result = smartcanvas.cards().search(search);
        System.out.println(result.getMeta());


//        for (Card card : result.cards()) {
//            System.out.println("Deleting: " + card.getId() + " - " + card.getMnemonic());
//            smartcanvas.cards().delete(card.getId());
//        }
        

    }

}
