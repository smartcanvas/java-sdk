package com.smartcanvas.model;

import com.google.api.client.util.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class Card extends GenericData {

    public enum CardStatus {
        // (any|approved|pending|expired|rejected)
        ANY, APPROVED, PENDING, EXPIRED, REJECTED;
        public static final String NAMES = "any, approved, pending, expired, rejected";
    }

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
    @Key
    private Community community;
    @Key
    private String publishDate;
    @Key
    private String expirationDate;
    @Key
    private Boolean autoApprove;
    @Key
    private Set<String> categories;
    @Key
    private Set<String> metaTags;
    @Key
    private List<Attachment> attachments;

    private DateTime createDate;
    private DateTime updateDate;
    private String jsonExtendedData;
    private String coordinates;
    private UserActivity userActivities;
    private Permission permission;

    @Key
    private Set<String> locales;

    public Card(ContentProvider contentProvider) {
        super();
        this.contentProvider = contentProvider;
        this.categories = Sets.newHashSet();
        this.metaTags = Sets.newHashSet();
        this.attachments = Lists.newArrayList();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMnemonic() {
        return mnemonic;
    }

    public void setMnemonic(String mnemonic) {
        this.mnemonic = mnemonic;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Community getCommunity() {
        return community;
    }

    public void setCommunity(Community community) {
        this.community = community;
    }

    public DateTime getCreateDate() {
        return createDate;
    }

    public void setCreateDate(DateTime createDate) {
        this.createDate = createDate;
    }

    public DateTime getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(DateTime updateDate) {
        this.updateDate = updateDate;
    }

    public DateTime getPublishDate() {
        return DateTime.parseRfc3339(this.publishDate);
    }

    public void setPublishDate(DateTime publishDate) {
        this.publishDate = publishDate.toString();
    }

    public DateTime getExpirationDate() {
        return DateTime.parseRfc3339(this.expirationDate);
    }

    public void setExpirationDate(DateTime expirationDate) {
        this.expirationDate = expirationDate.toString();
    }

    public Boolean getAutoApprove() {
        return autoApprove;
    }

    public void setAutoApprove(Boolean autoApprove) {
        this.autoApprove = autoApprove;
    }

    // public String getJsonExtendedData() {
    // return jsonExtendedData;
    // }
    //
    // public void setJsonExtendedData(final JsonNode jsonExtendedDataNode) {
    // this.jsonExtendedData =
    // jsonExtendedDataNode==null?null:jsonExtendedDataNode.toString();
    // }
    //
    // public void setJsonExtendedDataRaw(final String jsonExtendedData) {
    // this.jsonExtendedData = jsonExtendedData;
    // }

    // public String getCoordinates() {
    // return coordinates;
    // }
    //
    // public void setCoordinates(final JsonNode coordinatesNode) {
    // this.coordinates = coordinatesNode == null ? null :
    // coordinatesNode.toString();
    // }
    //
    // public void setCoordinatesRaw(String coordinates) {
    // this.coordinates = coordinates;
    // }

    public Set<String> getCategories() {
        return categories;
    }

    public void setCategories(Set<String> categories) {
        this.categories = categories;
    }

    public Set<String> getMetaTags() {
        return metaTags;
    }

    public void setMetaTags(Set<String> metaTags) {
        this.metaTags = metaTags;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public ContentProvider getContentProvider() {
        return contentProvider;
    }

    public void setContentProvider(ContentProvider contentProvider) {
        this.contentProvider = contentProvider;
    }

    public List<Attachment> getAttachments() {
        return attachments;
    }

    public void setAttachments(List<Attachment> attachments) {
        this.attachments = attachments;
    }

    public UserActivity getUserActivities() {
        return userActivities;
    }

    public void setUserActivities(UserActivity userActivities) {
        this.userActivities = userActivities;
    }

    public Permission getPermission() {
        return permission;
    }

    public void setPermission(Permission permission) {
        this.permission = permission;
    }

    public Set<String> getLocales() {
        return locales;
    }

    public void setLocales(Set<String> locales) {
        this.locales = locales;
    }

    public void addCategories(String... categories) {
        for (String category : categories) {
            this.categories.add(category);
        }
    }

    public void addMetaTags(String... metaTags) {
        for (String tag : metaTags) {
            this.metaTags.add(tag);
        }
    }

    public void addVideoAttachment(String videoUrl) {
        Attachment video = Attachment.video(videoUrl);
        this.addAttachment(video);
    }

    public void addPhotoAttachment(String photoUrl) {
        Attachment photo = Attachment.photo(photoUrl);
        this.addAttachment(photo);
    }

    public void addArticleAttachment(String articleUrl, String photoUrl) {
        Attachment article = Attachment.article(articleUrl, photoUrl);
        this.addAttachment(article);
    }

    public void addAttachment(Attachment attachment) {
        this.attachments.add(attachment);
    }

    public static class Attachment {

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

    public static class Permission {

        private Integer securityLevel = 0;
        private List<String> groups = new ArrayList<String>();
        private List<String> users = new ArrayList<String>();

        public Integer getSecurityLevel() {
            return securityLevel;
        }

        public void setSecurityLevel(Integer securityLevel) {
            this.securityLevel = securityLevel;
        }

        public List<String> getGroups() {
            return groups;
        }

        public void setGroups(List<String> groups) {
            this.groups = groups;
        }

        public List<String> getUsers() {
            return users;
        }

        public void setUsers(List<String> users) {
            this.users = users;
        }

        @Override
        public String toString() {
            return Objects.toStringHelper(this).add("securityLevel", securityLevel).add("groups", groups)
                    .add("users", users).toString();
        }
    }
    public Card compareDate(String publishDate, String expirationDate){
        if (expirationDate == null || expirationDate == null || (publishDate.compareTo(expirationDate) < 0)) {
            return this;
        }else
            throw new IllegalStateException("publishDate must be equal or less than expirationDate");
    }
    public static class ContentProvider {

        @Key
        private String id = null;
        @Key
        private String contentId = null;
        @Key
        private String contentURL = null;
        @Key
        private String userId = null;
        @Key
        private DateTime createDate = null;
        @Key
        private DateTime updateDate = null;

        public ContentProvider(String id, String contentId, String userId) {
            super();
            this.id = id;
            this.contentId = contentId;
            this.userId = userId;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getContentId() {
            return contentId;
        }

        public void setContentId(String contentId) {
            this.contentId = contentId;
        }

        public String getContentURL() {
            return contentURL;
        }

        public void setContentURL(String contentURL) {
            this.contentURL = contentURL;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public DateTime getCreateDate() {
            return createDate;
        }

        public void setCreateDate(DateTime createDate) {
            this.createDate = createDate;
        }

        public DateTime getUpdateDate() {
            return updateDate;
        }

        public void setUpdateDate(DateTime updateDate) {
            this.updateDate = updateDate;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("class ContentProvider {\n");

            sb.append("  id: ").append(id).append("\n");
            sb.append("  contentId: ").append(contentId).append("\n");
            sb.append("  contentURL: ").append(contentURL).append("\n");
            sb.append("  userId: ").append(userId).append("\n");
            sb.append("}\n");
            return sb.toString();
        }
    }

    public static class Author {
        @Key
        private String id = null;
        @Key
        private String displayName = null;
        @Key
        private String imageURL = null;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getDisplayName() {
            return displayName;
        }

        public void setDisplayName(String displayName) {
            this.displayName = displayName;
        }

        public String getImageURL() {
            return imageURL;
        }

        public void setImageURL(String imageURL) {
            this.imageURL = imageURL;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("class Author {\n");

            sb.append("  id: ").append(id).append("\n");
            sb.append("  displayName: ").append(displayName).append("\n");
            sb.append("  imageURL: ").append(imageURL).append("\n");
            sb.append("}\n");
            return sb.toString();
        }
    }

    public static class Community {

        private String id = null;
        private String displayName = null;

        /**
         * Community ID or mnemonic
         **/

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        /**
         * User-friendly community display name
         **/

        public String getDisplayName() {
            return displayName;
        }

        public void setDisplayName(String displayName) {
            this.displayName = displayName;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("class Community {\n");

            sb.append("  id: ").append(id).append("\n");
            sb.append("  displayName: ").append(displayName).append("\n");
            sb.append("}\n");
            return sb.toString();
        }
    }
}
