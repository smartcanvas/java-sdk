package com.smartcanvas.model;

import java.util.List;

import com.google.api.client.util.Key;
import com.google.api.client.util.Objects;

public class CardSearchResult {

    @Key
    private MetaResponse meta;
    
    @Key
    private List<Card> data;

    public MetaResponse getMeta() {
        return meta;
    }
   
    public Iterable<Card> cards() {
        return data;
    }

    @Override
    public String toString() {
        return Objects.toStringHelper(this)
                .add("meta", meta)
                .add("data", data)
                .toString();
    }
}
