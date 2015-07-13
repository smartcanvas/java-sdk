package com.smartcanvas.model;






public class Community  {
  
  private String id = null;
  private String displayName = null;

  
  /**
   * Community ID or mnemonic
   **/

  public String getId() {
    return id;
  }
  public void setId(String id) {
    this.id = id;
  }

  
  /**
   * User-friendly community display name
   **/

  public String getDisplayName() {
    return displayName;
  }
  public void setDisplayName(String displayName) {
    this.displayName = displayName;
  }

  

  @Override
  public String toString()  {
    StringBuilder sb = new StringBuilder();
    sb.append("class Community {\n");
    
    sb.append("  id: ").append(id).append("\n");
    sb.append("  displayName: ").append(displayName).append("\n");
    sb.append("}\n");
    return sb.toString();
  }
}
