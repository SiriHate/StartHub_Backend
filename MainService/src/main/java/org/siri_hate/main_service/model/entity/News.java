package org.siri_hate.main_service.model.entity;

import jakarta.persistence.*;
import org.siri_hate.main_service.model.entity.category.NewsCategory;

import java.time.LocalDate;

/**
 * This class represents the News entity.
 * It includes the id, title, user, previewUrl, category, content, publicationDate, and moderationPassed of the news.
 * It is mapped to the "news" table in the database.
 */
@Entity
@Table(name = "news")
public class News {

    /**
     * The id of the news.
     * It is the primary key in the "news" table.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    /**
     * The title of the news.
     */
    @Column(name = "title")
    private String title;

    /**
     * The user who wrote the news.
     * It is a foreign key referencing the "users" table.
     */
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    /**
     * The URL of the news's preview image.
     */
    @Column(name = "preview_url")
    private String previewUrl;

    /**
     * The category of the news.
     * It is a foreign key referencing the "news_categories" table.
     */
    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private NewsCategory category;

    /**
     * The content of the news.
     */
    @Lob
    @Column(name = "content")
    private String content;

    /**
     * The publication date of the news.
     */
    @Column(name = "publication_date")
    private LocalDate publicationDate;

    /**
     * Whether the news has passed moderation.
     */
    @Column(name = "moderation_passed")
    private Boolean moderationPassed;

    /**
     * Default constructor.
     */
    public News() { }

    /**
     * Constructor with all fields.
     *
     * @param title            the title of the news
     * @param user             the user who wrote the news
     * @param previewUrl       the URL of the news's preview image
     * @param category         the category of the news
     * @param content          the content of the news
     * @param publicationDate  the publication date of the news
     * @param moderationPassed whether the news has passed moderation
     */
    public News(
            String title,
            User user,
            String previewUrl,
            NewsCategory category,
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
     * @return the id of the news
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
     * @return the title of the news
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
     * @return the user who wrote the news
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
     * @return the URL of the news's preview image
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
     * @return the category of the news
     */
    public NewsCategory getCategory() {
        return category;
    }

    /**
     * @param category the category to set
     */
    public void setCategory(NewsCategory category) {
        this.category = category;
    }

    /**
     * @return the content of the news
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
     * @return the publication date of the news
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
     * @return whether the news has passed moderation
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