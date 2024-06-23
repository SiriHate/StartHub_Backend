package org.siri_hate.main_service.model.dto.response.news;

/**
 * This class represents a summary response for a news item.
 * It includes the essential details of a news item that can be sent as a response.
 */
public class NewsSummaryResponse {

    private Long id;
    private String title;
    private String owner;
    private String previewUrl;
    private String category;

    /**
     * Default constructor.
     */
    public NewsSummaryResponse() { }

    /**
     * Constructor with all fields.
     *
     * @param id         the id of the news item
     * @param title      the title of the news item
     * @param owner      the owner of the news item
     * @param previewUrl the preview URL of the news item
     * @param category   the category of the news item
     */
    public NewsSummaryResponse(Long id, String title, String owner, String previewUrl, String category) {
        this.id = id;
        this.title = title;
        this.owner = owner;
        this.previewUrl = previewUrl;
        this.category = category;
    }

    /**
     * @return the id of the news item
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return the title of the news item
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return the owner of the news item
     */
    public String getOwner() {
        return owner;
    }

    /**
     * @param owner the owner to set
     */
    public void setOwner(String owner) {
        this.owner = owner;
    }

    /**
     * @return the preview URL of the news item
     */
    public String getPreviewUrl() {
        return previewUrl;
    }

    /**
     * @param previewUrl the preview URL to set
     */
    public void setPreviewUrl(String previewUrl) {
        this.previewUrl = previewUrl;
    }

    /**
     * @return the category of the news item
     */
    public String getCategory() {
        return category;
    }

    /**
     * @param category the category to set
     */
    public void setCategory(String category) {
        this.category = category;
    }

}