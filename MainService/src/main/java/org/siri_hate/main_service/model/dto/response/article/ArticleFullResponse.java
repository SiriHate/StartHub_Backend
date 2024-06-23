package org.siri_hate.main_service.model.dto.response.article;

import java.time.LocalDate;

/**
 * This class represents the full response for an article.
 * It includes all the details of an article that can be sent as a response.
 */
public class ArticleFullResponse {

    private Long id;
    private String title;
    private String owner;
    private String previewUrl;
    private String category;
    private String content;
    private LocalDate publicationDate;

    /**
     * Default constructor.
     */
    public ArticleFullResponse() {
    }

    /**
     * Constructor with all fields.
     *
     * @param id              the id of the article
     * @param title           the title of the article
     * @param owner           the owner of the article
     * @param previewUrl      the preview URL of the article
     * @param category        the category of the article
     * @param content         the content of the article
     * @param publicationDate the publication date of the article
     */
    public ArticleFullResponse(
            Long id,
            String title,
            String owner,
            String previewUrl,
            String category,
            String content,
            LocalDate publicationDate
                              ) {
        this.id = id;
        this.title = title;
        this.owner = owner;
        this.previewUrl = previewUrl;
        this.category = category;
        this.content = content;
        this.publicationDate = publicationDate;
    }

    /**
     * @return the id of the article
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
     * @return the title of the article
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
     * @return the owner of the article
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
     * @return the preview URL of the article
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
     * @return the category of the article
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

    /**
     * @return the content of the article
     */
    public String getContent() {
        return content;
    }

    /**
     * @param content the content to set
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * @return the publication date of the article
     */
    public LocalDate getPublicationDate() {
        return publicationDate;
    }

    /**
     * @param publicationDate the publication date to set
     */
    public void setPublicationDate(LocalDate publicationDate) {
        this.publicationDate = publicationDate;
    }

}