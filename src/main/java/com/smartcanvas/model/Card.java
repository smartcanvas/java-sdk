package com.smartcanvas.model;

import java.util.List;
import java.util.Set;

import org.joda.time.DateTime;

import com.google.api.client.util.GenericData;
import com.google.api.client.util.Key;
import com.google.api.client.util.Lists;
import com.google.api.client.util.Sets;

public class Card extends GenericData {

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
		return DateTime.parse(this.publishDate);
	}

	public void setPublishDate(DateTime publishDate) {
		this.publishDate = publishDate.toString();
	}

	public DateTime getExpirationDate() {
	    return DateTime.parse(this.expirationDate);
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

    public void addCategories(String...categories) {
        for (String category : categories) {
            this.categories.add(category);
        }
    }

    public void addMetaTags(String...metaTags) {
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
    
    public void addAttachment(Attachment attachment) {
        this.attachments.add(attachment);
    }
    
}
