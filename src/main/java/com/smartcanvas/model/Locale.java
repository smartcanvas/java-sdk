package com.smartcanvas.model;

import java.util.*;




public class Locale  {
  
  private List<String> languages = new ArrayList<String>() ;
  private List<String> region = new ArrayList<String>() ;
  private String geoCode = null;
  private String address = null;
  private String placeName = null;

  

  public List<String> getLanguages() {
    return languages;
  }
  public void setLanguages(List<String> languages) {
    this.languages = languages;
  }

  

  public List<String> getRegion() {
    return region;
  }
  public void setRegion(List<String> region) {
    this.region = region;
  }

  
  /**
   * Geo code from where this card was created
   **/

  public String getGeoCode() {
    return geoCode;
  }
  public void setGeoCode(String geoCode) {
    this.geoCode = geoCode;
  }

  
  /**
   * Address from where this card was created
   **/
 
  public String getAddress() {
    return address;
  }
  public void setAddress(String address) {
    this.address = address;
  }

  
  /**
   * Name of the place from where this card was created
   **/

  public String getPlaceName() {
    return placeName;
  }
  public void setPlaceName(String placeName) {
    this.placeName = placeName;
  }

  

  @Override
  public String toString()  {
    StringBuilder sb = new StringBuilder();
    sb.append("class Locale {\n");
    
    sb.append("  languages: ").append(languages).append("\n");
    sb.append("  region: ").append(region).append("\n");
    sb.append("  geoCode: ").append(geoCode).append("\n");
    sb.append("  address: ").append(address).append("\n");
    sb.append("  placeName: ").append(placeName).append("\n");
    sb.append("}\n");
    return sb.toString();
  }
}
