package org.siri_hate.main_service.model.dto.request.news;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;


public class NewsFullRequest {

    @NotBlank(message = "Title should not be blank")
    String title;

    String previewUrl;

    @Positive(message = "Category id should be positive")
    Long categoryId;

    @NotBlank(message = "Content should not be blank")
    String content;

    
    public NewsFullRequest() { }

    
    public NewsFullRequest(String title, String previewUrl, Long categoryId, String content) {
        this.title = title;
        this.previewUrl = previewUrl;
        this.categoryId = categoryId;
        this.content = content;
    }

    
    public String getTitle() {
        return title;
    }

    
    public void setTitle(String title) {
        this.title = title;
    }

    
    public String getPreviewUrl() {
        return previewUrl;
    }

    
    public void setPreviewUrl(String previewUrl) {
        this.previewUrl = previewUrl;
    }

    
    public Long getCategoryId() {
        return categoryId;
    }

    
    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    
    public String getContent() {
        return content;
    }

    
    public void setContent(String content) {
        this.content = content;
    }

}