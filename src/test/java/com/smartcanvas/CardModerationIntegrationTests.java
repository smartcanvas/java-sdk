package com.smartcanvas;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.UUID;

import org.jose4j.lang.JoseException;
import org.junit.Test;

import com.smartcanvas.model.Card;
import com.smartcanvas.model.CardId;

public class CardModerationIntegrationTests extends AbstractSmartCanvasIntegrationTests{

    public CardModerationIntegrationTests() throws JoseException {
        super();
    }

//    @Test
//    public void test() throws IOException {
//       Card c = Card.newBuilder()
//               .withContentProvider("Acme", null, UUID.randomUUID().toString())
//               .withTitle(UUID.randomUUID().toString()).build();
//       CardId id = smartcanvas.cards().insert(c);
//       smartcanvas.moderations().approve(id);
//    }

}
