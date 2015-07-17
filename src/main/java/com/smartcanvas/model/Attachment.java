package com.smartcanvas.model;

import java.util.ArrayList;
import java.util.List;

import com.google.api.client.util.Key;
import com.google.api.client.util.Value;

public class Attachment  {
  
  public enum TypeEnum {
	  
	  @Value("photo")
	  PHOTO,
	  @Value("article")
	  ARTICLE,  
	  @Value("video")
	  VIDEO,  
	  @Value("drive")
	  DRIVE, 
  };
  
  @Key  
  private TypeEnum type = null;
  @Key
  private String displayName = null;
  @Key
  private String contentURL = null;
  @Key
  private List<Image> images = new ArrayList<Image>() ;
  @Key
  private String jsonExtendedData = null;

  

  public TypeEnum getType() {
    return type;
  }
  public void setType(TypeEnum type) {
    this.type = type;
  }

  

  public String getDisplayName() {
    return displayName;
  }
  public void setDisplayName(String displayName) {
    this.displayName = displayName;
  }

  


  public String getContentURL() {
    return contentURL;
  }
  public void setContentURL(String contentURL) {
    this.contentURL = contentURL;
  }

  

  public List<Image> getImages() {
    return images;
  }
  public void setImages(List<Image> images) {
    this.images = images;
  }

  

  public String getJsonExtendedData() {
    return jsonExtendedData;
  }
  public void setJsonExtendedData(String jsonExtendedData) {
    this.jsonExtendedData = jsonExtendedData;
  }

  

  @Override
  public String toString()  {
    StringBuilder sb = new StringBuilder();
    sb.append("class Attachment {\n");
    
    sb.append("  type: ").append(type).append("\n");
    sb.append("  displayName: ").append(displayName).append("\n");
    sb.append("  contentURL: ").append(contentURL).append("\n");
    sb.append("  images: ").append(images).append("\n");
    sb.append("  jsonExtendedData: ").append(jsonExtendedData).append("\n");
    sb.append("}\n");
    return sb.toString();
  }
}
