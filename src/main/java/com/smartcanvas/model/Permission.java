package com.smartcanvas.model;

import java.util.*;





public class Permission  {
  
  private Integer securityLevel = 0;
  private List<String> groups = new ArrayList<String>() ;
  private List<String> users = new ArrayList<String>() ;

  
  /**
   * minimum: 0.0
   * maximum: 9.9999999E7
   **/

  public Integer getSecurityLevel() {
    return securityLevel;
  }
  public void setSecurityLevel(Integer securityLevel) {
    this.securityLevel = securityLevel;
  }

  
  /**
   **/

  public List<String> getGroups() {
    return groups;
  }
  public void setGroups(List<String> groups) {
    this.groups = groups;
  }

  
  /**
   **/

  public List<String> getUsers() {
    return users;
  }
  public void setUsers(List<String> users) {
    this.users = users;
  }

  

  @Override
  public String toString()  {
    StringBuilder sb = new StringBuilder();
    sb.append("class Permission {\n");
    
    sb.append("  securityLevel: ").append(securityLevel).append("\n");
    sb.append("  groups: ").append(groups).append("\n");
    sb.append("  users: ").append(users).append("\n");
    sb.append("}\n");
    return sb.toString();
  }
}
