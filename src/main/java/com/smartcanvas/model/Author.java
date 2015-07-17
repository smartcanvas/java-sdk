package com.smartcanvas.model;

import com.google.api.client.util.Key;

public class Author  {
  @Key
  private String id = null;
  @Key
  private String displayName = null;
  @Key
  private String imageURL = null;

  
  
  public String getId() {
    return id;
  }
  public void setId(String id) {
    this.id = id;
  }

  

  public String getDisplayName() {
    return displayName;
  }
  public void setDisplayName(String displayName) {
    this.displayName = displayName;
  }


  public String getImageURL() {
    return imageURL;
  }
  public void setImageURL(String imageURL) {
    this.imageURL = imageURL;
  }

  

  @Override
  public String toString()  {
    StringBuilder sb = new StringBuilder();
    sb.append("class Author {\n");
    
    sb.append("  id: ").append(id).append("\n");
    sb.append("  displayName: ").append(displayName).append("\n");
    sb.append("  imageURL: ").append(imageURL).append("\n");
    sb.append("}\n");
    return sb.toString();
  }
}