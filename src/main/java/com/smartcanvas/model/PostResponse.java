package com.smartcanvas.model;






public class PostResponse  {
  
  private String cardId = null;
  private String cardMnemonic = null;

  

  public String getCardId() {
    return cardId;
  }
  public void setCardId(String cardId) {
    this.cardId = cardId;
  }

  

  public String getCardMnemonic() {
    return cardMnemonic;
  }
  public void setCardMnemonic(String cardMnemonic) {
    this.cardMnemonic = cardMnemonic;
  }

  

  @Override
  public String toString()  {
    StringBuilder sb = new StringBuilder();
    sb.append("class PostResponse {\n");
    
    sb.append("  cardId: ").append(cardId).append("\n");
    sb.append("  cardMnemonic: ").append(cardMnemonic).append("\n");
    sb.append("}\n");
    return sb.toString();
  }
}
