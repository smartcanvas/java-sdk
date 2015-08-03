package com.smartcanvas;

import java.io.IOException;
import java.util.Iterator;

import com.smartcanvas.CardSearchRequest.CardSearchRequestBuilder;
import com.smartcanvas.model.Card;
import com.smartcanvas.model.CardSearchResult;

/**
 * Abstract Card API pagination complexities
 * 
 * @author fabio
 *
 */
public class CardIterator implements Iterator<Card> {

    private Smartcanvas smartcanvas;
    private CardSearchRequestBuilder searchRequestBuilder;
    private CardSearchResult result;
    private int currentPosition;
    private int totalCount;
    private int limit = 100;
    private int pageSize = 100;
    

    public CardIterator(Smartcanvas smartcanvas, CardSearchRequestBuilder searchRequestBuilder) throws IOException {
        super();
        this.smartcanvas = smartcanvas;
        this.searchRequestBuilder = searchRequestBuilder;
        this.searchRequestBuilder.limit(limit);
        fetchNextPage(0);
    }

    private void fetchNextPage(Integer offset) throws IOException {
        searchRequestBuilder.offset(offset);
        result = this.smartcanvas.cards().search(searchRequestBuilder.build());
    }

    @Override
    public boolean hasNext() {
        return result != null && result.getMeta().getCount() > 0 && currentPosition < result.getMeta().getCount();
    }

    @Override
    public Card next() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException();
    }
    
}