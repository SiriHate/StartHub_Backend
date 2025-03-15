package org.siri_hate.main_service.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.LocalDate;
import org.siri_hate.main_service.model.entity.category.ArticleCategory;

@Entity
@Table(name = "articles")
public class Article {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long id;

  @Column(name = "title")
  private String title;

  @ManyToOne
  @JoinColumn(name = "user_id", nullable = false)
  private User user;

  @Column(name = "preview_url")
  private String previewUrl;

  @ManyToOne
  @JoinColumn(name = "category_id", nullable = false)
  private ArticleCategory category;

  @Lob
  @Column(name = "content")
  private String content;

  @Column(name = "publication_date")
  private LocalDate publicationDate;

  @Column(name = "moderation_passed")
  private Boolean moderationPassed;

  public Article() {}

  public Article(
      String title,
      User user,
      String previewUrl,
      ArticleCategory category,
      String content,
      LocalDate publicationDate,
      Boolean moderationPassed) {
    this.title = title;
    this.user = user;
    this.previewUrl = previewUrl;
    this.category = category;
    this.content = content;
    this.publicationDate = publicationDate;
    this.moderationPassed = moderationPassed;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  public String getPreviewUrl() {
    return previewUrl;
  }

  public void setPreviewUrl(String previewUrl) {
    this.previewUrl = previewUrl;
  }

  public ArticleCategory getCategory() {
    return category;
  }

  public void setCategory(ArticleCategory category) {
    this.category = category;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public LocalDate getPublicationDate() {
    return publicationDate;
  }

  public void setPublicationDate(LocalDate publicationDate) {
    this.publicationDate = publicationDate;
  }

  public Boolean getModerationPassed() {
    return moderationPassed;
  }

  public void setModerationPassed(Boolean moderationPassed) {
    this.moderationPassed = moderationPassed;
  }
}
