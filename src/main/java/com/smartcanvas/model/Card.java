package com.smartcanvas.model;

import java.util.List;

import com.google.api.client.util.GenericData;
import com.google.api.client.util.Key;


public class Card  extends GenericData {
  
  @Key
  private Long id;
  @Key
  private String mnemonic;
  @Key
  private String title;
  @Key
  private String summary;
  @Key
  private String content;
  @Key
  private Author author;
  @Key
  private ContentProvider contentProvider;


  private Community community;
  private Long createDate;
  private Long updateDate;
  private Long publishDate;
  private Long expirationDate;
  private Boolean autoApprove;
  private String jsonExtendedData;
  private String coordinates;
  private List<String> categories;
  private List<String> metaTags;
  private List<Attachment> attachments;
  private UserActivity userActivities;
  private Permission permission;
  private List<String> locales;


}
