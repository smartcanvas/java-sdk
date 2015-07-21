package com.smartcanvas.model;

import com.google.api.client.util.Key;

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
        StringBuilder sb = new StringBuilder();
        sb.append("class Image {\n");

        sb.append("  url: ").append(url).append("\n");
        sb.append("  type: ").append(type).append("\n");
        sb.append("  height: ").append(height).append("\n");
        sb.append("  width: ").append(width).append("\n");
        sb.append("  originalURL: ").append(originalURL).append("\n");
        sb.append("}\n");
        return sb.toString();
    }
}
