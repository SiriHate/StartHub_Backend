package org.siri_hate.main_service.service;

import org.siri_hate.main_service.model.dto.request.news.NewsFullRequest;
import org.siri_hate.main_service.model.dto.response.news.NewsFullResponse;
import org.siri_hate.main_service.model.dto.response.news.NewsSummaryResponse;
import org.siri_hate.main_service.model.entity.News;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface NewsService {

  void createNews(String username, NewsFullRequest news);

  NewsFullResponse getNewsById(Long id);

  Page<NewsSummaryResponse> getNewsByCategoryAndSearchQuery(
      String category, String query, Pageable pageable);

  Page<NewsSummaryResponse> getAllNews(Pageable pageable);

  Page<NewsSummaryResponse> getModeratedNews(Pageable pageable);

  Page<NewsSummaryResponse> getUnmoderatedNews(Pageable pageable);

  void updateNews(Long id, NewsFullRequest news);

  void deleteNews(Long id);

  void updateNewsModerationStatus(Long id, Boolean moderationPassed);
}
