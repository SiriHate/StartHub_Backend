package org.siri_hate.main_service.service;

import org.siri_hate.main_service.model.dto.request.news.NewsFullRequest;
import org.siri_hate.main_service.model.dto.response.news.NewsFullResponse;
import org.siri_hate.main_service.model.dto.response.news.NewsSummaryResponse;
import org.siri_hate.main_service.model.entity.News;

import java.util.List;

public interface NewsService {

    void createNews(String username, NewsFullRequest news);

    NewsFullResponse getNewsById(Long id);

    List<NewsSummaryResponse> getNewsByCategory(String category);

    List<NewsSummaryResponse> getAllNews();

    List<NewsSummaryResponse> searchNewsByOwnerUsername(String username);

    void updateNews(Long id, News newsDetails);

    void deleteNews(Long id);

}
