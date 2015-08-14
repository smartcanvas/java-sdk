package com.smartcanvas.model;

import java.util.*;

import com.google.api.client.util.DateTime;
import com.google.api.client.util.GenericData;
import com.google.api.client.util.Key;
import com.google.api.client.util.Lists;
import com.google.api.client.util.Objects;
import com.google.api.client.util.Sets;
import com.google.api.client.util.Value;

public class Card extends GenericData {

    public enum CardStatus {
        @Value("ANY")
        ANY, @Value("APPROVED")
        APPROVED, @Value("PENDING")
        PENDING, @Value("EXPIRED")
        EXPIRED, @Value("REJECTED")
        REJECTED;
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
    @Key
    private DateTime createDate;
    @Key
    private DateTime updateDate;
    @Key
    private Object jsonExtendedData;
    @Key
    private CardStatus status;
    private String coordinates;
    @Key
    private List<UserActivity> userActivities;
    @Key
    private Permission permission;
    @Key
    private Set<String> locales;

    public Card() {
        super();
    }

    public Card(ContentProvider contentProvider) {
        this();
        this.contentProvider = contentProvider;
    }

    public Card(String mnemonic, String title, String summary, String content, Author author,
            ContentProvider contentProvider, Community community, String publishDate, String expirationDate,
            Boolean autoApprove, Set<String> categories, Set<String> metaTags, List<Attachment> attachments,
            Object jsonExtendedData, Permission permission, Set<String> locales) {
        super();
        this.mnemonic = mnemonic;
        this.title = title;
        this.summary = summary;
        this.content = content;
        this.author = author;
        this.contentProvider = contentProvider;
        this.community = community;
        this.publishDate = publishDate;
        this.expirationDate = expirationDate;
        this.autoApprove = autoApprove;
        this.categories = categories;
        this.metaTags = metaTags;
        this.attachments = attachments;
        this.jsonExtendedData = jsonExtendedData;
        this.permission = permission;
        this.locales = locales;
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

    public CardStatus status() {
        return this.status;
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

    public List<UserActivity> getUserActivities() {
        return userActivities;
    }

    public void setUserActivities(List<UserActivity> userActivities) {
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
        if (getCategories() == null) {
            setCategories(Sets.<String>newHashSet());
        }
        for (String category : categories) {
            this.categories.add(category);
        }
    }

    public void addMetaTags(String... metaTags) {
        if (getMetaTags() == null) {
            setMetaTags(Sets.<String>newHashSet());
        }
        for (String tag : metaTags) {
            this.metaTags.add(tag);
        }
    }

    public static class Attachment {

        public enum TypeEnum {

            @Value("photo")
            PHOTO, @Value("article")
            ARTICLE, @Value("video")
            VIDEO, @Value("drive")
            @Deprecated
            DRIVE,

        }

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

        public Attachment() {
        }

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

        @Override
        public String toString() {
            return Objects.toStringHelper(this).add("type", this.type).add("displayName", this.displayName)
                    .add("contentURL", this.contentURL).add("embedURL", this.embedURL).add("images", this.images)
                    .add("jsonExtendedData", this.jsonExtendedData).toString();
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

        public ContentProvider() {
            super();
        }

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
            return Objects.toStringHelper(this).add("id", this.id).add("contentId", this.contentId)
                    .add("contentURL", this.contentURL).add("userId", this.userId).add("createDate", this.createDate)
                    .add("updateDate", this.updateDate).toString();
        }
    }

    public static class Author {
        @Key
        private String id = null;
        @Key
        private String displayName = null;
        @Key
        private String imageURL = null;
        @Key
        private Image image;

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
            return Objects.toStringHelper(this).add("image", this.image).add("imageURL", this.imageURL)
                    .add("displayName", this.displayName).add("id", this.id).toString();
        }

    }

    public static class Community {

        @Key
        private String id = null;
        @Key
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
            return Objects.toStringHelper(this).add("id", this.id).add("displayName", this.displayName).toString();
        }

    }

    public void setJsonExtendedData(Object jsonExtendedData) {
        this.jsonExtendedData = jsonExtendedData;
    }

    public static class CardBuilder {
        private String mnemonic;
        private String title;
        private String summary;
        private String content;
        private Author author;
        private ContentProvider contentProvider;
        private Community community;
        private String publishDate;
        private String expirationDate;
        private Boolean autoApprove;
        private Set<String> categories;
        private Set<String> metaTags;
        private List<Attachment> attachments = new ArrayList<>();
        private Object jsonExtendedData;
        private Permission permission;
        private Set<String> locales;

        public CardBuilder() {
        }

        public CardBuilder(Card bean) {
            this.mnemonic = bean.mnemonic;
            this.title = bean.title;
            this.summary = bean.summary;
            this.content = bean.content;
            this.author = bean.author;
            this.contentProvider = bean.contentProvider;
            this.community = bean.community;
            this.publishDate = bean.publishDate;
            this.expirationDate = bean.expirationDate;
            this.autoApprove = bean.autoApprove;
            this.categories = bean.categories;
            this.metaTags = bean.metaTags;
            this.attachments = bean.attachments;
            this.jsonExtendedData = bean.jsonExtendedData;
            this.permission = bean.permission;
            this.locales = bean.locales;
        }

        public CardBuilder withMnemonic(String mnemonic) {
            this.mnemonic = mnemonic;
            return this;
        }

        public CardBuilder withTitle(String title) {
            this.title = title;
            return this;
        }

        public CardBuilder withSummary(String summary) {
            this.summary = summary;
            return this;
        }


        public CardBuilder withContent(String content) {
            this.content = content;
            return this;
        }

        public CardBuilder withAuthor(Author author) {
            this.author = author;
            return this;
        }

        public CardBuilder withContentProvider(ContentProvider contentProvider) {
            this.contentProvider = contentProvider;
            return this;
        }
        
        public CardBuilder withContentProvider(String providerId, String providerUserId, String providerContentId) {
            this.contentProvider = new ContentProvider(providerId, providerContentId, providerUserId);
            return this;
        }

        public CardBuilder withContentProvider(String id, String userId, String contentId, DateTime updated) {
            this.contentProvider = new ContentProvider(id, contentId, userId);
            this.contentProvider.setUpdateDate(updated);
            return this;
        }
        
        public CardBuilder withCommunity(Community community) {
            this.community = community;
            return this;
        }

        public CardBuilder withPublishDate(String publishDate) {
            this.publishDate = publishDate;
            return this;
        }

        public CardBuilder withExpirationDate(String expirationDate) {
            this.expirationDate = expirationDate;
            return this;
        }

        public CardBuilder withAutoApprove(Boolean autoApprove) {
            this.autoApprove = autoApprove;
            return this;
        }

        public CardBuilder withCategories(String... categories) {
            Set<String> categorie = new HashSet<String>(Arrays.<String>asList(categories));
            this.categories = categorie;
            return this;
        }

        public CardBuilder withMetaTags(String... metaTags) {
            Set<String> metaTag = new HashSet<String>(Arrays.<String>asList(metaTags));
            this.metaTags = metaTag;
            return this;
        }

        public CardBuilder withAttachments(List<Attachment> attachments) {
            this.attachments = attachments;
            return this;
        }
        public CardBuilder withVideoAttachment(String videoUrl) {
            Attachment video = Attachment.video(videoUrl);
            return this.addAttachment(video);
        }

        public CardBuilder withPhotoAttachment(String photoUrl) {
            Attachment photo = Attachment.photo(photoUrl);
            this.addAttachment(photo);
            return this;
        }

        public CardBuilder withArticleAttachment(String articleUrl, String photoUrl) {
            Attachment article = Attachment.article(articleUrl, photoUrl);
            return this.addAttachment(article);
        }

        public CardBuilder addAttachment(Attachment attachment) {
            this.attachments.add(attachment);
            return this;
        }

        public CardBuilder withJsonExtendedData(Object jsonExtendedData) {
            this.jsonExtendedData = jsonExtendedData;
            return this;
        }

        public CardBuilder withPermission(Permission permission) {
            this.permission = permission;
            return this;
        }

        public CardBuilder withLocales(Set<String> locales) {
            this.locales = locales;
            return this;
        }

        public Card build() {
            return new Card(mnemonic, title, summary, content, author, contentProvider, community, publishDate,
                    expirationDate, autoApprove, categories, metaTags, attachments, jsonExtendedData, permission,
                    locales);
        }
    }

    public static CardBuilder newBuilder() {
        return new CardBuilder();
    }

}
