package com.smartcanvas.model;

import com.google.api.client.util.Key;

public class Image  {
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
	  
}
  public Image(String url, String type, Integer height, Integer width, String originalURL) {
		super();
		this.url = url;
		this.type = type;
		this.height = height;
		this.width = width;
		this.originalURL = originalURL;
	}

  
  
  
  /**
   * Image URL
   **/

  public String getUrl() {
    return url;
  }
  public void setUrl(String url) {
    this.url = url;
  }

  
  /**
   * Type of this image
   **/

  public String getType() {
    return type;
  }
  public void setType(String type) {
    this.type = type;
  }

  
  /**
   * Image height
   **/

  public Integer getHeight() {
    return height;
  }
  public void setHeight(Integer height) {
    this.height = height;
  }

  
  /**
   * Image width
   **/
 
  public Integer getWidth() {
    return width;
  }
  public void setWidth(Integer width) {
    this.width = width;
  }

  
  /**
   * Original image URL on the content provider
   **/

  public String getOriginalURL() {
    return originalURL;
  }
  public void setOriginalURL(String originalURL) {
    this.originalURL = originalURL;
  }

  

  @Override
  public String toString()  {
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
