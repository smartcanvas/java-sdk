package com.smartcanvas.model;

import com.smartcanvas.model.MetaResponse;
import java.util.*;
import com.smartcanvas.model.Card;



public class GetResponse  {
  
  private MetaResponse meta = null;
  private List<Card> data = new ArrayList<Card>() ;

  

  public MetaResponse getMeta() {
    return meta;
  }
  public void setMeta(MetaResponse meta) {
    this.meta = meta;
  }

  

  public List<Card> getData() {
    return data;
  }
  public void setData(List<Card> data) {
    this.data = data;
  }

  

  @Override
  public String toString()  {
    StringBuilder sb = new StringBuilder();
    sb.append("class GetResponse {\n");
    
    sb.append("  meta: ").append(meta).append("\n");
    sb.append("  data: ").append(data).append("\n");
    sb.append("}\n");
    return sb.toString();
  }
}
