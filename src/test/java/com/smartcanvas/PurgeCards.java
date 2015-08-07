package com.smartcanvas;

import com.smartcanvas.model.Card;
import com.smartcanvas.model.CardSearchResult;
import org.jose4j.lang.JoseException;
import org.junit.Test;

import java.io.IOException;

public class PurgeCards extends AbstractSmartCanvasIntegrationTests {

    public PurgeCards() throws JoseException {
        super();
    }

    @Test
    public void purgeCards() throws IOException {
        
        
        CardSearchRequest search = CardSearchRequest.builder(true)
                //.maxAge(15)
                //.initDate(new DateTime("2014-07-15"))
//                .status(CardStatus.APPROVED)
//                .fields("id", "mnemonic")
//                .limit(100)
//                .offset(50)
                /* FIXME - NOT IMPLEMENTED THE LOGIC TO BUILD THE URL*/
               // .jsonExtendedData()

                .build();


        System.out.println(search);
        CardSearchResult result = smartcanvas.cards().search(search);
        System.out.println(result.getMeta());
//        for (Card card : result.cards()) {
//            System.out.println("Deleting: " + card.getId() + " - " + card.getMnemonic());
//            smartcanvas.cards().delete(card.getId());
//        }
        

    }

}
