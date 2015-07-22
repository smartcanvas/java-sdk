package com.smartcanvas.model;

import java.util.*;

import com.google.api.client.util.Key;

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
        StringBuilder sb = new StringBuilder();
        sb.append("class MetaResponse {\n");

        sb.append("  count: ").append(count).append("\n");
        sb.append("  pos: ").append(pos).append("\n");
        sb.append("  facets: ").append(facets).append("\n");
        sb.append("}\n");
        return sb.toString();
    }
}
