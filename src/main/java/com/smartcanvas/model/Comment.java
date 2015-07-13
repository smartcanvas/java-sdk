package com.smartcanvas.model;






public class Comment  {
  
  private Long id = null;
  private Long personId = null;
  private String personEmail = null;
  private String text = null;
  private Long createDate = null;

  
  /**
   * Comment ID
   **/
  
  public Long getId() {
    return id;
  }
  public void setId(Long id) {
    this.id = id;
  }

  
  /**
   * Person ID (*TODO se email vai virar chave, esse campo não faz mais sentido)
   **/

  public Long getPersonId() {
    return personId;
  }
  public void setPersonId(Long personId) {
    this.personId = personId;
  }

  
  /**
   * Person email
   **/

  public String getPersonEmail() {
    return personEmail;
  }
  public void setPersonEmail(String personEmail) {
    this.personEmail = personEmail;
  }

  
  /**
   * Comment text (content)
   **/

  public String getText() {
    return text;
  }
  public void setText(String text) {
    this.text = text;
  }

  
  /**
   * Timestamp of this comment creation
   **/

  public Long getCreateDate() {
    return createDate;
  }
  public void setCreateDate(Long createDate) {
    this.createDate = createDate;
  }

  

  @Override
  public String toString()  {
    StringBuilder sb = new StringBuilder();
    sb.append("class Comment {\n");
    
    sb.append("  id: ").append(id).append("\n");
    sb.append("  personId: ").append(personId).append("\n");
    sb.append("  personEmail: ").append(personEmail).append("\n");
    sb.append("  text: ").append(text).append("\n");
    sb.append("  createDate: ").append(createDate).append("\n");
    sb.append("}\n");
    return sb.toString();
  }
}
