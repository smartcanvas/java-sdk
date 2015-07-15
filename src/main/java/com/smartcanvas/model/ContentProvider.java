package com.smartcanvas.model;

import com.google.api.client.util.GenericData;
import com.google.api.client.util.Key;
import org.joda.time.DateTime;

public class ContentProvider  extends GenericData {
  
  @Key
  private String id = null;
  @Key
  private String contentId = null;
  @Key
  private String contentURL = null;
  @Key
  private String userId = null;
  @Key
  private DateTime createDate = null;
  @Key
  private DateTime updateDate = null;

  

  public ContentProvider(String id, String contentId, String userId) {
	super();
	this.id = id;
	this.contentId = contentId;
	this.userId = userId;
}
  
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

  public DateTime getCreateDate() {
	    return createDate;
	  }
	  public void setCreateDate(DateTime createDate) {
	    this.createDate = createDate;
	  }

	  
  public DateTime getUpdateDate() {
    return updateDate;
  }
  public void setUpdateDate(DateTime updateDate) {
    this.updateDate = updateDate;
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
