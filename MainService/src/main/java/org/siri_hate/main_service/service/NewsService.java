package org.siri_hate.main_service.service;

import org.siri_hate.main_service.model.entity.News;

import java.util.List;

public interface NewsService {

    News createNews(News news);

    News getNewsById(Long id);

    List<News> getNewsByCategory(String category);

    List<News> getAllNews();

    List<News> searchNewsByOwnerUsername(String username);

    void updateNews(Long id, News newsDetails);

    void deleteNews(Long id);

}
