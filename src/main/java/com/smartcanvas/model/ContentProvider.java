package com.smartcanvas.model;





public class ContentProvider  {
  
  private String id = null;
  private String contentId = null;
  private String contentURL = null;
  private String userId = null;

  

  public String getId() {
    return id;
  }
  public void setId(String id) {
    this.id = id;
  }

  

  public String getContentId() {
    return contentId;
  }
  public void setContentId(String contentId) {
    this.contentId = contentId;
  }


  public String getContentURL() {
    return contentURL;
  }
  public void setContentURL(String contentURL) {
    this.contentURL = contentURL;
  }

  

  public String getUserId() {
    return userId;
  }
  public void setUserId(String userId) {
    this.userId = userId;
  }

  

  @Override
  public String toString()  {
    StringBuilder sb = new StringBuilder();
    sb.append("class ContentProvider {\n");
    
    sb.append("  id: ").append(id).append("\n");
    sb.append("  contentId: ").append(contentId).append("\n");
    sb.append("  contentURL: ").append(contentURL).append("\n");
    sb.append("  userId: ").append(userId).append("\n");
    sb.append("}\n");
    return sb.toString();
  }
}
