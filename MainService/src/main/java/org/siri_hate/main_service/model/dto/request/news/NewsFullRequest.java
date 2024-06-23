package org.siri_hate.main_service.model.dto.request.news;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

/**
 * This class represents a full request for a News item.
 * It includes fields for the title, preview URL, category ID, and content of the news item.
 * The title and content fields are mandatory, and the category ID must be a positive number.
 */
public class NewsFullRequest {

    @NotBlank(message = "Title should not be blank")
    String title;

    String previewUrl;

    @Positive(message = "Category id should be positive")
    Long categoryId;

    @NotBlank(message = "Content should not be blank")
    String content;

    /**
     * Default constructor for NewsFullRequest.
     */
    public NewsFullRequest() { }

    /**
     * Constructor for NewsFullRequest with all fields.
     *
     * @param title      the title of the news item
     * @param previewUrl the preview URL of the news item
     * @param categoryId the category ID of the news item
     * @param content    the content of the news item
     */
    public NewsFullRequest(String title, String previewUrl, Long categoryId, String content) {
        this.title = title;
        this.previewUrl = previewUrl;
        this.categoryId = categoryId;
        this.content = content;
    }

    /**
     * Returns the title of the news item.
     *
     * @return the title of the news item
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets the title of the news item.
     *
     * @param title the title of the news item
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Returns the preview URL of the news item.
     *
     * @return the preview URL of the news item
     */
    public String getPreviewUrl() {
        return previewUrl;
    }

    /**
     * Sets the preview URL of the news item.
     *
     * @param previewUrl the preview URL of the news item
     */
    public void setPreviewUrl(String previewUrl) {
        this.previewUrl = previewUrl;
    }

    /**
     * Returns the category ID of the news item.
     *
     * @return the category ID of the news item
     */
    public Long getCategoryId() {
        return categoryId;
    }

    /**
     * Sets the category ID of the news item.
     *
     * @param categoryId the category ID of the news item
     */
    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    /**
     * Returns the content of the news item.
     *
     * @return the content of the news item
     */
    public String getContent() {
        return content;
    }

    /**
     * Sets the content of the news item.
     *
     * @param content the content of the news item
     */
    public void setContent(String content) {
        this.content = content;
    }

}