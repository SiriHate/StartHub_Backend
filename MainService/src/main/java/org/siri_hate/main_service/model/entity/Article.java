package org.siri_hate.main_service.model.entity;

import jakarta.persistence.*;
import org.siri_hate.main_service.model.entity.category.ArticleCategory;

import java.time.LocalDate;

/**
 * This class represents the Article entity.
 * It includes the id, title, user, previewUrl, category, content, publicationDate, and moderationPassed of the article.
 * It is mapped to the "articles" table in the database.
 */
@Entity
@Table(name = "articles")
public class Article {

    /**
     * The id of the article.
     * It is the primary key in the "articles" table.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    /**
     * The title of the article.
     */
    @Column(name = "title")
    private String title;

    /**
     * The user who wrote the article.
     * It is a foreign key referencing the "users" table.
     */
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    /**
     * The URL of the article's preview image.
     */
    @Column(name = "preview_url")
    private String previewUrl;

    /**
     * The category of the article.
     * It is a foreign key referencing the "article_categories" table.
     */
    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private ArticleCategory category;

    /**
     * The content of the article.
     */
    @Lob
    @Column(name = "content")
    private String content;

    /**
     * The publication date of the article.
     */
    @Column(name = "publication_date")
    private LocalDate publicationDate;

    /**
     * Whether the article has passed moderation.
     */
    @Column(name = "moderation_passed")
    private Boolean moderationPassed;

    /**
     * Default constructor.
     */
    public Article() { }

    /**
     * Constructor with all fields.
     *
     * @param title            the title of the article
     * @param user             the user who wrote the article
     * @param previewUrl       the URL of the article's preview image
     * @param category         the category of the article
     * @param content          the content of the article
     * @param publicationDate  the publication date of the article
     * @param moderationPassed whether the article has passed moderation
     */
    public Article(
            String title,
            User user,
            String previewUrl,
            ArticleCategory category,
            String content,
            LocalDate publicationDate,
            Boolean moderationPassed
                  ) {
        this.title = title;
        this.user = user;
        this.previewUrl = previewUrl;
        this.category = category;
        this.content = content;
        this.publicationDate = publicationDate;
        this.moderationPassed = moderationPassed;
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
     * @return the user who wrote the article
     */
    public User getUser() {
        return user;
    }

    /**
     * @param user the user to set
     */
    public void setUser(User user) {
        this.user = user;
    }

    /**
     * @return the URL of the article's preview image
     */
    public String getPreviewUrl() {
        return previewUrl;
    }

    /**
     * @param previewUrl the previewUrl to set
     */
    public void setPreviewUrl(String previewUrl) {
        this.previewUrl = previewUrl;
    }

    /**
     * @return the category of the article
     */
    public ArticleCategory getCategory() {
        return category;
    }

    /**
     * @param category the category to set
     */
    public void setCategory(ArticleCategory category) {
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
     * @param publicationDate the publicationDate to set
     */
    public void setPublicationDate(LocalDate publicationDate) {
        this.publicationDate = publicationDate;
    }

    /**
     * @return whether the article has passed moderation
     */
    public Boolean getModerationPassed() {
        return moderationPassed;
    }

    /**
     * @param moderationPassed the moderationPassed to set
     */
    public void setModerationPassed(Boolean moderationPassed) {
        this.moderationPassed = moderationPassed;
    }

}