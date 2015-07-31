package com.smartcanvas.model;

import java.util.List;

import com.google.api.client.util.Key;
import com.google.api.client.util.Objects;

public class MetaResponse {

    @Key
    private Integer count;
    @Key
    private Integer pos;
    @Key
    private List<String> facets;

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Integer getPos() {
        return pos;
    }

    public void setPos(Integer pos) {
        this.pos = pos;
    }

    public List<String> getFacets() {
        return facets;
    }

    public void setFacets(List<String> facets) {
        this.facets = facets;
    }

    @Override
    public String toString() {
        return Objects.toStringHelper(this)
                .add("count", count)
                .add("pos", pos)
                .add("facets", facets)
                .toString();
    }
}
