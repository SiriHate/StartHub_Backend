package org.siri_hate.main_service.model.dto.request.article;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

/**
 * This class represents a full request for an Article.
 * It includes fields for the title, preview URL, category ID, and content of the article.
 * The title and content fields are mandatory, and the category ID must be a positive number.
 */
public class ArticleFullRequest {

    @NotBlank
    String title;

    String previewUrl;

    @Positive
    Long categoryId;

    @NotBlank
    String content;

    /**
     * Default constructor for ArticleFullRequest.
     */
    public ArticleFullRequest() { }

    /**
     * Constructor for ArticleFullRequest with all fields.
     *
     * @param title      the title of the article
     * @param previewUrl the preview URL of the article
     * @param categoryId the category ID of the article
     * @param content    the content of the article
     */
    public ArticleFullRequest(String title, String previewUrl, Long categoryId, String content) {
        this.title = title;
        this.previewUrl = previewUrl;
        this.categoryId = categoryId;
        this.content = content;
    }

    /**
     * Returns the title of the article.
     *
     * @return the title of the article
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets the title of the article.
     *
     * @param title the title of the article
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Returns the preview URL of the article.
     *
     * @return the preview URL of the article
     */
    public String getPreviewUrl() {
        return previewUrl;
    }

    /**
     * Sets the preview URL of the article.
     *
     * @param previewUrl the preview URL of the article
     */
    public void setPreviewUrl(String previewUrl) {
        this.previewUrl = previewUrl;
    }

    /**
     * Returns the category ID of the article.
     *
     * @return the category ID of the article
     */
    public Long getCategoryId() {
        return categoryId;
    }

    /**
     * Sets the category ID of the article.
     *
     * @param categoryId the category ID of the article
     */
    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    /**
     * Returns the content of the article.
     *
     * @return the content of the article
     */
    public String getContent() {
        return content;
    }

    /**
     * Sets the content of the article.
     *
     * @param content the content of the article
     */
    public void setContent(String content) {
        this.content = content;
    }

}