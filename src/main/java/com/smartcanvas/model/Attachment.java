package com.smartcanvas.model;

import java.util.ArrayList;
import java.util.List;

import com.google.api.client.util.Key;
import com.google.api.client.util.Value;

public class Attachment {

    public enum TypeEnum {

        @Value("photo")
        PHOTO, @Value("article")
        ARTICLE, @Value("video")
        VIDEO,
    };

    @Key
    private TypeEnum type = null;
    @Key
    private String displayName = null;
    @Key
    private String contentURL = null;
    @Key
    private String embedURL = null;
    @Key
    private List<Image> images = new ArrayList<Image>();
    @Key
    private String jsonExtendedData = null;

    public Attachment(TypeEnum type) {
        this.type = type;
    }

    public TypeEnum getType() {
        return type;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getContentURL() {
        return contentURL;
    }

    public void setContentURL(String contentURL) {
        this.contentURL = contentURL;
    }

    public String getEmbedURL() {
        return embedURL;
    }

    public void setEmbedURL(String embedURL) {
        this.embedURL = embedURL;
    }

    public List<Image> getImages() {
        return images;
    }

    public void setImages(List<Image> images) {
        this.images = images;
    }

    public String getJsonExtendedData() {
        return jsonExtendedData;
    }

    public void setJsonExtendedData(String jsonExtendedData) {
        this.jsonExtendedData = jsonExtendedData;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class Attachment {\n");
        sb.append("  type: ").append(type).append("\n");
        sb.append("  displayName: ").append(displayName).append("\n");
        sb.append("  contentURL: ").append(contentURL).append("\n");
        sb.append("  embedURL: ").append(embedURL).append("\n");
        sb.append("  images: ").append(images).append("\n");
        sb.append("  jsonExtendedData: ").append(jsonExtendedData).append("\n");
        sb.append("}\n");
        return sb.toString();
    }

    public static Attachment video(String videoUrl) {
        Attachment videoattachment = new Attachment(TypeEnum.VIDEO);
        videoattachment.setContentURL(videoUrl);
        videoattachment.setEmbedURL(videoUrl);
        return videoattachment;
    }

    public static Attachment photo(String photoUrl) {
        Attachment photoAttachment = new Attachment(TypeEnum.PHOTO);
        photoAttachment.setContentURL(photoUrl);
        photoAttachment.addImage(photoUrl);
        return photoAttachment;
    }

    public static Attachment article(String articleUrl, String photoUrl) {
    	Attachment articleAttachment = new Attachment(TypeEnum.ARTICLE);
    	articleAttachment.setContentURL(articleUrl);
    	articleAttachment.addImage(photoUrl);
    	return articleAttachment;
         
         
    }

    private void addImage(String photoUrl) {
        this.images.add(new Image(photoUrl));
    }


}
