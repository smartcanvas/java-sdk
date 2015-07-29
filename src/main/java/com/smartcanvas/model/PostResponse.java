package com.smartcanvas.model;

import com.google.api.client.util.Key;


public class PostResponse {
  
  @Key
  private String id;
  
  @Key
  private String mnemonic;

  public String id() {
    return id;
  }

  public String mnemonic() {
    return mnemonic;
  }

  @Override
  public String toString()  {
    StringBuilder sb = new StringBuilder();
    sb.append("class PostResponse {\n");
    sb.append("  id: ").append(id).append("\n");
    sb.append("  mnemonic: ").append(mnemonic).append("\n");
    sb.append("}\n");
    return sb.toString();
  }
}
