package com.smartcanvas.model;






public class Activity  {
  
  private String type = null;
  private Integer counter = null;
  private String target = null;

  

  public String getType() {
    return type;
  }
  public void setType(String type) {
    this.type = type;
  }

  
  /**
   * how many of this activities this card has
   **/

  public Integer getCounter() {
    return counter;
  }
  public void setCounter(Integer counter) {
    this.counter = counter;
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
    sb.append("class Activity {\n");
    
    sb.append("  type: ").append(type).append("\n");
    sb.append("  counter: ").append(counter).append("\n");
    sb.append("  target: ").append(target).append("\n");
    sb.append("}\n");
    return sb.toString();
  }
}
