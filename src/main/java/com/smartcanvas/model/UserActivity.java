package com.smartcanvas.model;



public class UserActivity  {
  
  private String type = null;
  private String target = null;

  
  /**
   * type of this user activity (example = like, dislike etc)
   **/
 
  public String getType() {
    return type;
  }
  public void setType(String type) {
    this.type = type;
  }

  
  /**
   * optional value that represents the target of this action. Examples = facebook, twitter, linkedin (for sharing)
   **/

  public String getTarget() {
    return target;
  }
  public void setTarget(String target) {
    this.target = target;
  }

  

  @Override
  public String toString()  {
    StringBuilder sb = new StringBuilder();
    sb.append("class UserActivity {\n");
    
    sb.append("  type: ").append(type).append("\n");
    sb.append("  target: ").append(target).append("\n");
    sb.append("}\n");
    return sb.toString();
  }
}
