package org.siri_hate.main_service.model.dto.response.article;

import java.time.LocalDate;


public class ArticleFullResponse {

    private Long id;
    private String title;
    private String owner;
    private String previewUrl;
    private String category;
    private String content;
    private LocalDate publicationDate;

    
    public ArticleFullResponse() {
    }

    
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

    
    public String getOwner() {
        return owner;
    }

    
    public void setOwner(String owner) {
        this.owner = owner;
    }

    
    public String getPreviewUrl() {
        return previewUrl;
    }

    
    public void setPreviewUrl(String previewUrl) {
        this.previewUrl = previewUrl;
    }

    
    public String getCategory() {
        return category;
    }

    
    public void setCategory(String category) {
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

}