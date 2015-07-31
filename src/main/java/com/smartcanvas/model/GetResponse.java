package com.smartcanvas.model;

import java.util.List;
import java.util.Set;

import com.google.api.client.util.Key;

public class GetResponse {

    @Key
    private MetaResponse meta;
    
    @Key
    private List<Card> data;

    @Key
    private List authorIds;

    @Key
    private String q;

    public List<String> getAuthorIds() {
        return authorIds;
    }

    public void setAuthorIds(String authorIdss) {
        this.authorIds = authorIds;
    }

    public String getQuery() {
        return q;
    }

    public void setQuery(String q) {
        this.q = q;
    }

    public MetaResponse getMeta() {
        return meta;
    }

    public void setMeta(MetaResponse meta) {
        this.meta = meta;
    }

    public List<Card> getData() {
        return data;
    }

    public void setData(List<Card> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class GetResponse {\n");

        sb.append("  meta: ").append(meta).append("\n");
        sb.append("  data: ").append(data).append("\n");
        sb.append("  query: ").append(q).append("\n");
        sb.append("  authorIds: ").append(authorIds).append("\n");
        sb.append("}\n");
        return sb.toString();
    }
}
