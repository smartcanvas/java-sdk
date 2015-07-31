package com.smartcanvas.model;

import com.google.api.client.util.Key;
import com.google.api.client.util.Objects;

public class Image {

    @Key
    private String url = null;
    @Key
    private String type = null;
    @Key
    private Integer height = null;
    @Key
    private Integer width = null;
    @Key
    private String originalURL = null;

    public Image() {
        super();
    }
    
    public Image(String originalURL) {
        super();
        this.originalURL = originalURL;
    }

    public Image(String type, Integer height, Integer width, String originalURL) {
        this(originalURL);
        this.type = type;
        this.height = height;
        this.width = width;
    }

    /**
     * Image URL
     **/
    public String getUrl() {
        return url;
    }

    public Image setUrl(String url) {
        this.url = url;

        return this;
    }

    /**
     * Type of this image
     **/

    public String getType() {
        return type;
    }

    public Image setType(String type) {
        this.type = type;
        return this;
    }

    /**
     * Image height
     **/

    public Integer getHeight() {
        return height;
    }

    public Image setHeight(Integer height) {
        this.height = height;
        return this;
    }

    /**
     * Image width
     **/

    public Integer getWidth() {
        return width;
    }

    public Image setWidth(Integer width) {
        this.width = width;
        return this;
    }

    /**
     * Original image URL on the content provider
     **/

    public String getOriginalURL() {
        return originalURL;
    }

    public Image setOriginalURL(String originalURL) {
        this.originalURL = originalURL;
        return this;
    }

    @Override
    public String toString() {
        return Objects.toStringHelper(this).add("url", this.url).add("type", this.type).add("height", this.height)
                .add("width", this.width).add("originalURL", this.originalURL).toString();
    }
}
